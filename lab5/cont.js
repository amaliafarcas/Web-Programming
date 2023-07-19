$(document).ready(function() {
    var sliderWidth = $('#slider .slide').width();
    var windowWidth = $(window).width();

    $('#slider .slide').css({
        'width': sliderWidth + 'px',
        'transition': 'left 500ms ease-in-out' // Add CSS transition
    });

    function slide() {
        $('#slider .slide').animate({
            'left': '-=' + (sliderWidth + 10) + 'px'
        }, 5000, 'linear', function() {
            $('#slider .slide').append($('#slider .slide img:first-child').clone()); // Append a copy of the first image to the end
            $('#slider .slide img:first-child').remove(); // Remove the first image
            $('#slider .slide').css({
                'left': '0px'
            });
            slide();
        });
    }

    slide();

    $('#slider .slide img').on('click', function() {
        $('#slider .slide').stop();
        var src = $(this).attr('src');
        $('#slider .lightbox').html('<img src="' + src + '">');
        $('#slider .lightbox').fadeIn();
    });

    $('#slider .lightbox').on('click', function() {
        $('#slider .lightbox').fadeOut(function() {
            $('#slider .lightbox').html('');
            slide();
        });
    });


});
