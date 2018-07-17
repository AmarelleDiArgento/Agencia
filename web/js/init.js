document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.collapsible');
    var instances = M.Collapsible.init(elems, options);
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems, options);
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems, options);
  });
(function ($) {
    $(function () {

        $('.sidenav').sidenav();
        $('.modal').modal();
        $('.slider').slider();
        $('.collapsible').collapsible();
        $('.fixed-action-btn').floatingActionButton();
        $('.tooltipped').tooltip();
        $('.materialboxed').materialbox();
        $('.tabs').tabs();
        $('select').formSelect();
        $('.carousel').carousel();
        $('.carousel.carousel-slider').carousel();

    }); // end of document ready
})(jQuery); // end of jQuery name space

