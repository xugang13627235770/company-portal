function zuopin_list(){
	$('.zuopin_list div').hover(
		function(){
			if(!$(this).children('.info').is(':animated')){
    		$(this).addClass('on').children('.info').fadeIn(200);
			}
		},
		function(){
				$(this).removeClass('on').children('.info').fadeOut(250);
		}
	);
}