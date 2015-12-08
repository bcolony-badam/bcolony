"use strict";
var EnvProps = {
	templatesPath : "assets/templates/",
	templateExt : ".html",
	lablesURL : "assets/json/lables.json",
	userDataURL : "assets/json/userdata.json"
};

var Lables = {
	lableMap : {},
	loadLables : function() {
		var self = this;
		$.ajax({
			url : EnvProps.lablesURL,
			type : 'GET',
			dataType : "json",
			async : false
		}).done(function(data) {
			Handlebars.registerHelper('lbl', function(options) {
				return options.fn(data);
			});
			self.lableMap = data;
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
		});
	},
	get : function(key) {
		return this.lableMap[key];
	}
};
var Templates = {
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
		var self = this;
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "json"
		}).done(function(data) {
			self.render("main-content", tmpl, data);
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
	}
};

(function($) {
	$.fn.serializeObject = function() {
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
							topOffset = 100;
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
//---------------------------------------------------//
//Start breeding colony specific
function jsGridController(mainurl) {
	var instance = {};
    instance.loadData = function() {
        var d = $.Deferred();
        $.ajax({
        	url : mainurl,
            dataType: "json"
        }).done(function(response) {
            d.resolve(response);
        });
        return d.promise();
    };
    instance.insertItem = function(item) {
    	var d = $.Deferred();
        $.ajax({
            type: "POST",
            url: mainurl,
            data: JSON.stringify(item),
            dataType: "json",
            contentType : "application/json"
        }).done(function(response) {
        	item.id=response;
            d.resolve(item);
        });
        return d.promise();
    };
    instance.updateItem = function(item) {
    	var d = $.Deferred();
        return $.ajax({
            type: "PUT",
            url: mainurl+"/"+item.id,
            data: JSON.stringify(item),
            contentType : "application/json"
        }).done(function() {
            d.resolve(item);
        });
        return d.promise();
    };
    instance.deleteItem = function(item) {
    	var d = $.Deferred();
        return $.ajax({
            type: "DELETE",
            url: mainurl+"/"+item.id
        }).done(function() {
            d.resolve(item);
        });
        return d.promise();
    };	
    return instance;
}
jsGrid.setDefaults({
	height: "auto",
    width: "100%",

    filtering: false,
    editing: true,
    sorting: true,
    paging: true,
    autoload: true,
    inserting:true,
    pageLoading:false,
    loadIndication:false,
    pageSize: 20
});

var MasterData = {
	load : function(hash) {
			$("#main-content").html("<table id='jsGrid'></table>");
			$("#jsGrid").jsGrid({
			    pageButtonCount: 3,
			    controller: jsGridController(hash),
			    fields: [
			        { name: "name", type: "text" },
			        { name: "description", type: "textarea"},
			        { type: "control" }
			    ]
			});
	},
    breadcrumb:function(hash) {
    	return {"data":[{"path":"index.html","name":"Dashborad"},{"path":"animalgroups","name":"animalgroups"}]};
    }
};

function renderMainContent(hash) {
	MasterData.load(hash);
}
function setPageTitle(hash) {
	//TODO
}
function renderBreadCrumb(hash) {
	Templates.render("top-nav-content","topnav",MasterData.breadcrumb(hash));
}
$(document).ready(function() {
	Lables.loadLables();
	$('#sidebar-left,breadcrumb').on('click', 'a', function(e) {
		e.preventDefault();
		var url = $(this).attr('href');
		window.location.hash = url;
		if ($(this).attr('href') == '#') {
			return;
		} else {
			setPageTitle(url);
			renderBreadCrumb(url);
			renderMainContent(url);
		}
	});
});
