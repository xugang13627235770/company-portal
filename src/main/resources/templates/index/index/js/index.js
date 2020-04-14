window.onload=function(){
	var aPicLi=document.getElementById('pic_list').getElementsByTagName('li');
	var aTextLi=document.getElementById('text_list').getElementsByTagName('li');
	var aIcoLi=document.getElementById('ico_list').getElementsByTagName('li');
	var oIcoUl=document.getElementById('ico_list').getElementsByTagName('ul')[0];
	var oPrev=document.getElementById('btn_prev');
	var oNext=document.getElementById('btn_next');
	var oDiv=document.getElementById('box');
	var i=0;
	var iNowUlLeft=0;
	var iNow=0;
	
	oPrev.onclick=function(){
		if(iNowUlLeft>0){
			iNowUlLeft--;
			oUlleft();
		}
		oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
		oNext.className=iNowUlLeft==(aIcoLi.length-7)?'btn':'btn showBtn';
	}
	
	oNext.onclick=function(){
		if(iNowUlLeft < aIcoLi.length-7){
			iNowUlLeft++;
			oIcoUl.style.left=-aIcoLi[0].offsetWidth*iNowUlLeft+'px';
		}
		oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
		oNext.className=iNowUlLeft==(aIcoLi.length-7)?'btn':'btn showBtn';
	}
	
	for(i=0;i<aIcoLi.length;i++){
		aIcoLi[i].index=i;
		aIcoLi[i].onclick=function(){
			if(iNow==this.index){
				return false;
			}
			iNow=this.index;
			tab();
		}
	}
	
	function tab(){
		for(i=0;i<aIcoLi.length;i++){
			aIcoLi[i].className='';
			aPicLi[i].style.filter='alpha(opacity:0)';
			aPicLi[i].style.opacity=0;
			aTextLi[i].getElementsByTagName('h2')[0].className='';
			miaovStopMove( aPicLi[i]);
		}
		aIcoLi[iNow].className='active';
		//aPicLi[this.index].style.filter='alpha(opacity:100)';
		//aPicLi[this.index].style.opacity=1;
		miaovStartMove(aPicLi[iNow],{opacity:100},MIAOV_MOVE_TYPE.BUFFER);
		aTextLi[iNow].getElementsByTagName('h2')[0].className='show';
	}
	
	function oUlleft(){
		oIcoUl.style.left=-aIcoLi[0].offsetWidth*iNowUlLeft+'px';
	}
	
	function autoplay(){
		iNow++;
		if(iNow>=aIcoLi.length){
			iNow=0;
		}
		if(iNow<iNowUlLeft){
			iNowUlLeft=iNow;
		}else if(iNow>=iNowUlLeft+7){
			iNowUlLeft=iNow-6;
		}
		oPrev.className=iNowUlLeft==0?'btn':'btn showBtn';
		oNext.className=iNowUlLeft==(aIcoLi.length-7)?'btn':'btn showBtn';
		oUlleft();
		tab();
	}
	
	var time=setInterval(autoplay,3000);
	oDiv.onmouseover=function(){
		clearInterval(time);
	}
	oDiv.onmouseout=function(){
		time=setInterval(autoplay,3000);
	}

}