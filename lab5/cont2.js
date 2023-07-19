$(document).ready(function() {
    var sliderWidth = $('#slider img').length * $('#slider img').width();
    $('#slider').css('width', sliderWidth);

    function animateSlider() {
        $('#slider').animate({marginLeft: -sliderWidth}, 5000, 'linear', function() {
            $(this).css({marginLeft: 0}).find('img:last').after($(this).find('img:first'));
            animateSlider();
        });
    }

    animateSlider();


    $('#slider .slide img').on('click', function() {
        $('#slider .slide').stop();
        var src = $(this).attr('src');
        $('#slider .lightbox').html('<img src="' + src + '">');
        $('#slider .lightbox').fadeIn();
    });

    $('#slider .lightbox').on('click', function() {
        $('#slider .lightbox').fadeOut(function() {
            $('#slider .lightbox').html('');
            animateSlider();
        });
    });
});
