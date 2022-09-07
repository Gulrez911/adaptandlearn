function scrollTabs(param){
  let hidWidth;
let scrollBarWidths = 40;

let widthOfList = function(){
  let itemsWidth = 0;
  $(`#${param} li`).each(function(){
    let itemWidth = $(this).outerWidth();
    itemsWidth+=itemWidth;
  });
  return itemsWidth;
};

var widthOfHidden = function(){
  return (($(`#${param}-wrapper `+'.wrapper').outerWidth())-widthOfList()-getLeftPosi())-scrollBarWidths;
};

let getLeftPosi = function(){
  return $(`#${param}`).position().left;
};

var reAdjust = function(){
  if (($(`#${param}-wrapper `+'.wrapper').outerWidth()) < widthOfList()) {
    $(`#${param}-wrapper `+'.scroller-right').show();
  }
  else {
    $(`#${param}-wrapper `+'.scroller-right').hide();
  }
  
  if (getLeftPosi()<0) {
    $(`#${param}-wrapper `+'.scroller-left').show();
  }
  else {
    $(`#${param}-wrapper `+'.item').animate({left:"-="+getLeftPosi()+"px"},'slow');
  	$(`#${param}-wrapper `+'.scroller-left').hide();
  }
}

reAdjust();

$(window).on('resize',function(e){  
  	reAdjust();
});
let ScrollPosArray=[];
$(`#${param}-wrapper `+'.scroller-right').click(function() {
  
  $(`#${param}-wrapper `+'.scroller-left').fadeIn('slow');
  
  
  let widthOfHidden1 = (-widthOfHidden() < $('.wrapper').outerWidth()) ?          widthOfHidden():-$('.wrapper').outerWidth() 
  
  ScrollPosArray.push(widthOfHidden1);
  console.log(ScrollPosArray)
  $(`#${param}`).animate({left:"+="+widthOfHidden1+"px"},'slow',function(){
   // console.log(widthOfHidden())
    if(-widthOfHidden() <1 ){
      $(`#${param}-wrapper `+'.scroller-right').fadeOut('slow');
    }
  });
});

$(`#${param}-wrapper `+'.scroller-left').click(function() {
  if(ScrollPosArray.length !==0){
    $(`#${param}`).animate({left:"-="+ScrollPosArray[ScrollPosArray.length -1]+"px"},'slow',function(){
  	ScrollPosArray.pop();
      if(ScrollPosArray.length ==0){
        $(`#${param}-wrapper `+'.scroller-right').fadeIn('slow');
	$(`#${param}-wrapper `+'.scroller-left').fadeOut('slow');
      }
      	
  	});
  }
  console.log(ScrollPosArray)
});    
}
scrollTabs("list");
scrollTabs("list1");