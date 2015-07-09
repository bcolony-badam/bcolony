"use strict";
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

function LoadSelect2Script(callback){
if (!$.fn.select2){
	$.getScript('plugins/select2/select2.min.js', callback);
}
else {
	if (callback && typeof(callback) === "function") {
		callback();
	}
}
}
/*-------------------------------------------
Main scripts used by theme
---------------------------------------------*/
//
//Function for load content from url and put in $('.ajax-content') block
//
function LoadAjaxContent(url){
$('.preloader').show();
$.ajax({
	mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
	url: url,
	type: 'GET',
	success: function(data) {
		$('#ajax-content').html(data);
		$('.preloader').hide();
	},
	error: function (jqXHR, textStatus, errorThrown) {
		alert(errorThrown);
	},
	dataType: "html",
	async: false
});
}
//
//Function maked all .box selector is draggable, to disable for concrete element add class .no-drop
//
function WinMove(){
$( "div.box").not('.no-drop')
	.draggable({
		revert: true,
		zIndex: 2000,
		cursor: "crosshair",
		handle: '.box-name',
		opacity: 0.8
	})
	.droppable({
		tolerance: 'pointer',
		drop: function( event, ui ) {
			var draggable = ui.draggable;
			var droppable = $(this);
			var dragPos = draggable.position();
			var dropPos = droppable.position();
			draggable.swap(droppable);
			setTimeout(function() {
				var dropmap = droppable.find('[id^=map-]');
				var dragmap = draggable.find('[id^=map-]');
				if (dragmap.length > 0 || dropmap.length > 0){
					dragmap.resize();
					dropmap.resize();
				}
				else {
					draggable.resize();
					droppable.resize();
				}
			}, 50);
			setTimeout(function() {
				draggable.find('[id^=map-]').resize();
				droppable.find('[id^=map-]').resize();
			}, 250);
		}
	});
}
//
//Swap 2 elements on page. Used by WinMove function
//
jQuery.fn.swap = function(b){
b = jQuery(b)[0];
var a = this[0];
var t = a.parentNode.insertBefore(document.createTextNode(''), a);
b.parentNode.insertBefore(a, b);
t.parentNode.insertBefore(b, t);
t.parentNode.removeChild(t);
return this;
};
//
//Helper for open ModalBox with requested header, content and bottom
//
//
function OpenModalBox(header, inner, bottom){
var modalbox = $('#modalbox');
modalbox.find('.modal-header-name span').html(header);
modalbox.find('.devoops-modal-inner').html(inner);
modalbox.find('.devoops-modal-bottom').html(bottom);
modalbox.fadeIn('fast');
$('body').addClass("body-expanded");
}
//
//Close modalbox
//
//
function CloseModalBox(){
var modalbox = $('#modalbox');
modalbox.fadeOut('fast', function(){
	modalbox.find('.modal-header-name span').children().remove();
	modalbox.find('.devoops-modal-inner').children().remove();
	modalbox.find('.devoops-modal-bottom').children().remove();
	$('body').removeClass("body-expanded");
});
}
//
//Function convert values of inputs in table to JSON data
//
//
function Table2Json(table) {
var result = {};
table.find("tr").each(function () {
	var oneRow = [];
	var varname = $(this).index();
	$("td", this).each(function (index) { if (index != 0) {oneRow.push($("input", this).val());}});
	result[varname] = oneRow;
});
var result_json = JSON.stringify(result);
OpenModalBox('Table to JSON values', result_json);
}
//MAIN DOCUMENT READY SCRIPT OF DEVOOPS THEME
//
//In this script main logic of theme
//
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
$(document).ready(function () {

$('body').on('click', '.show-sidebar', function (e) {
	e.preventDefault();
	$('div#main').toggleClass('sidebar-show');
	setTimeout(MessagesMenuWidth, 250);
});

var ajax_url = location.hash.replace(/^#/, '');
if (ajax_url.length < 1) {
	ajax_url = 'ajax/dashboard.html';
}
LoadAjaxContent(ajax_url);

$('.main-menu').on('click', 'a', function (e) {
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
		LoadAjaxContent(url);
	}
	if ($(this).attr('href') == '#') {
		e.preventDefault();
	}
});

var height = window.innerHeight - 49;
$('#main').css('min-height', height)
	.on('click', '.expand-link', function (e) {
		var body = $('body');
		e.preventDefault();
		var box = $(this).closest('div.box');
		var button = $(this).find('i');
		button.toggleClass('fa-expand').toggleClass('fa-compress');
		box.toggleClass('expanded');
		body.toggleClass('body-expanded');
		var timeout = 0;
		if (body.hasClass('body-expanded')) {
			timeout = 100;
		}
		setTimeout(function () {
			box.toggleClass('expanded-padding');
		}, timeout);
		setTimeout(function () {
			box.resize();
			box.find('[id^=map-]').resize();
		}, timeout + 50);
	})
	.on('click', '.collapse-link', function (e) {
		e.preventDefault();
		var box = $(this).closest('div.box');
		var button = $(this).find('i');
		var content = box.find('div.box-content');
		content.slideToggle('fast');
		button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
		setTimeout(function () {
			box.resize();
			box.find('[id^=map-]').resize();
		}, 50);
	})
	.on('click', '.close-link', function (e) {
		e.preventDefault();
		var content = $(this).closest('div.box');
		content.remove();
	});

$('body').on('click', 'a.close-link', function(e){
	e.preventDefault();
	CloseModalBox();
});
$('#top-panel').on('click','a', function(e){
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
		LoadAjaxContent(url);
	}
});

});


