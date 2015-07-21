"use strict";
var EnvProps = {
	envName : "dev",
	templatesPath : "assets/templates/",
	templateExt : ".html",
	lablesURL : "assets/json/lables.json",
	userDataURL : "assets/json/userdata.json"
};
var CORE = {
	Templates : {
		getTemplate : function(name) {
			if (Handlebars.templates === undefined
					|| Handlebars.templates[name] === undefined) {
				$.ajax({
					url : EnvProps.templatesPath + name + EnvProps.templateExt,
					async : false
				}).done(function(data) {
					if (Handlebars.templates === undefined) {
						Handlebars.templates = {};
					}
					Handlebars.templates[name] = Handlebars.compile(data);
				}).fail(function(jqXHR, textStatus, errorThrown) {
					return Handlebars.compile("");
				});
			}
			return Handlebars.templates[name];
		},
		render : function(id, tmplName, data) {
			var compiledTmpl = this.getTemplate(tmplName);
			$("#" + id).html(compiledTmpl(data));
		},
		getContent : function(tmplName, data) {
			var compiledTmpl = this.getTemplate(tmplName);
			return compiledTmpl(data);
		},
		renderMainContent : function(url, tmpl) {
			$("#preloader").show();
			var self = this;
			$.ajax({
				url : url,
				type : 'GET',
				dataType : "json"
			}).done(function(data) {
				self.render("main-content", tmpl, data);
				$("#preloader").hide();
			}).fail(function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			});
		},
		renderContent : function(url, tmpl, id) {
			var self = this;
			$.ajax({
				url : url,
				type : 'GET',
				dataType : "json"
			}).done(function(data) {
				self.render(id, tmpl, data);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			});
		},
		renderTable : function(url) {
			$("#preloader").show();
			var self = this;
			$.ajax({
				url : url,
				type : 'GET',
				dataType : "json"
			}).done(function(data) {
				// Need to implemet js-grid
				$("#preloader").hide();
			}).fail(function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			});
		}
	},
	Lables : {
		loadLables : function() {
			$.ajax({
				url : EnvProps.lablesURL,
				type : 'GET',
				dataType : "json"
			}).done(function(data) {
				Handlebars.registerHelper('lbl', function(options) {
					return options.fn(data);
				});
			}).fail(function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			});
		}
	}
};
(function($) {
	$.fn.serializeObject = function() {
		"use strict";

		var result = {};
		var extend = function(i, element) {
			var node = result[element.name];
			if ('undefined' !== typeof node && node !== null) {
				if ($.isArray(node)) {
					node.push(element.value);
				} else {
					result[element.name] = [ node, element.value ];
				}
			} else {
				result[element.name] = element.value;
			}
		};

		$.each(this.serializeArray(), extend);
		return result;
	};

})(jQuery);
$(function() {
	$(window)
			.bind(
					"load resize",
					function() {
						var topOffset = 50;
						var width = (this.window.innerWidth > 0) ? this.window.innerWidth
								: this.screen.width;
						if (width < 768) {
							$('div.navbar-collapse').addClass('collapse');
							topOffset = 100; // 2-row-menu
						} else {
							$('div.navbar-collapse').removeClass('collapse');
						}

						var height = ((this.window.innerHeight > 0) ? this.window.innerHeight
								: this.screen.height) - 1;
						height = height - topOffset;
						if (height < 1)
							height = 1;
						if (height > topOffset) {
							$("#page-wrapper").css("min-height",
									(height) + "px");
						}
					});

	var url = window.location;
	var element = $('ul.nav a').filter(function() {
		return this.href == url || url.href.indexOf(this.href) == 0;
	}).addClass('active').parent().parent().addClass('in').parent();
	if (element.is('li')) {
		element.addClass('active');
	}
});

$(document).ready(function() {
	CORE.Lables.loadLables();
	$('#sidebar-left,breadcrumb').on('click', 'a', function(e) {
		e.preventDefault();
		var url = $(this).attr('href');
		window.location.hash = url;
		if ($(this).attr('href') == '#') {
			return;
		} else {
			$("#preloader").show();
			CORE.Templates.render("main-content", "grid");
			$("#preloader").hide();
		}
	});
});
