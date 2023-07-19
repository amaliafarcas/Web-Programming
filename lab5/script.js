$(document).ready(function() {
    const numImages = $('.slide img').length;
    const imgWidth = $('.slide img').outerWidth(true);
    const screenWidth = window.innerWidth;
    //var imagesOnScreen = Math.floor(screenWidth/imgWidth);
    //console.log(imagesOnScreen);

    let currentPosition = 0;

    //var slideWidth = numImages * imgWidth;
   // var lastImagePosition = imgWidth/3;

    function startSlider() {
        let slideInterval = setInterval(function() {
            currentPosition += 1;
            $('#slider .slide img').css('transform', 'translateX(' + currentPosition + 'px)');
            //lastImagePosition -= 1;

            if (currentPosition  >= imgWidth) {
                $('#slider .slide').prepend($('#slider .slide img:last-child').clone());
                $('#slider .slide img:last-child').remove();
                //lastImagePosition = imgWidth/3;
                currentPosition = 0;
            }
        }, 20);


        $('#slider .slide').on('click', 'img', function() {
            clearInterval(slideInterval);
            const imgSrc = $(this).attr('src');
            const lightboxImg = $('<img>').attr('src', imgSrc);
            $('#slider .lightbox').html(lightboxImg).fadeIn();
        });
    }

    $('#slider .lightbox').on('click', function() {
        $(this).fadeOut(function() {
            startSlider();
        });
    });

    startSlider();
});
