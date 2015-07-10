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
					|| Handlebars.templates[name] === undefined
					|| EnvProps.envName === "dev") {
				$.ajax({
					url : EnvProps.templatesPath + name + EnvProps.templateExt,
					success : function(data) {
						if (Handlebars.templates === undefined) {
							Handlebars.templates = {};
						}
						Handlebars.templates[name] = Handlebars.compile(data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						return Handlebars.compile("");
					},
					async : false
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
				success : function(data) {
					self.render("main-content", tmpl, data);
					$("#preloader").hide();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType : "json",
				async : false
			});
		},
		renderContent : function(url, tmpl, id) {
			var self = this;
			$.ajax({
				url : url,
				type : 'GET',
				success : function(data) {
					self.render(id, tmpl, data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType : "json",
				async : false
			});
		},
		renderTable : function(url) {
			$("#preloader").show();
			var self = this;
			$.ajax({
				url : url,
				type : 'GET',
				success : function(data) {
					// Need to implemet js-grid
					$("#preloader").hide();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType : "json",
				async : false
			});
		}
	},
	Lables : {
		loadLables : function() {
			$.ajax({
				url : EnvProps.lablesURL,
				type : 'GET',
				success : function(data) {
					Handlebars.registerHelper('lbl', function(options) {
						return options.fn(data);
					});
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType : "json",
				async : false
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

$(document).ready(function() {
	CORE.Lables.loadLables();
	
	$('#sidebar-left').on('click', 'a', function (e) {
		var parents = $(this).parents('li');
		var li = $(this).closest('li.dropdown');
		var another_items = $('.main-menu li').not(parents);
		another_items.find('a').removeClass('active');
		another_items.find('a').removeClass('active-parent');
		if ($(this).hasClass('dropdown-toggle') || $(this).closest('li').find('ul').length == 0) {
			$(this).addClass('active-parent');
			var current = $(this).next();
			if (current.is(':visible')) {
				li.find("ul.dropdown-menu").slideUp('fast');
				li.find("ul.dropdown-menu a").removeClass('active')
			}
			else {
				another_items.find("ul.dropdown-menu").slideUp('fast');
				current.slideDown('fast');
			}
		}
		else {
			if (li.find('a.dropdown-toggle').hasClass('active-parent')) {
				var pre = $(this).closest('ul.dropdown-menu');
				pre.find("li.dropdown").not($(this).closest('li')).find('ul.dropdown-menu').slideUp('fast');
			}
		}
		if ($(this).hasClass('active') == false) {
			$(this).parents("ul.dropdown-menu").find('a').removeClass('active');
			$(this).addClass('active')
		}
		if ($(this).hasClass('ajax-link')) {
			e.preventDefault();
			if ($(this).hasClass('add-full')) {
				$('#content').addClass('full-content');
			}
			else {
				$('#content').removeClass('full-content');
			}
			var url = $(this).attr('href');
			window.location.hash = url;
		}
		if ($(this).attr('href') == '#') {
			e.preventDefault();
		}
	});
});
