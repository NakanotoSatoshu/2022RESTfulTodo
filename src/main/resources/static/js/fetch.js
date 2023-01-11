//axiosに変更
window.onload= function(){
	let obj = 0;
	let global = 0;
	//let b = document.querySelector("tr").classList.contains('inComp');
	//if(fales = b){
	//	document.querySelector("tr").classList.toggle("inComp");
	//}


	/** 完了ボタンを押したときの処理. Fetch*/
	var table = document.querySelector(".table");
	table.addEventListener("click",(event) =>{
		let target = event.target;
		let bl = target.classList.contains("btn-complete");
		if(bl){
			let id = target.getAttribute('href').split("/")[2];
			obj = target;
			global = id;
			getComplete(id);
		};
	});
	  /** 未完了ボタンを押したときの処理. Fecth*/
	var table = document.querySelector(".table");
	table.addEventListener("click",(event) =>{
		let target = event.target;
		let bl = target.classList.contains("btn-incomplete");
		if(bl){
			let id = target.getAttribute('href').split("/")[2];
			obj = target;
			global = id;
			getInComplete(id);
		};
	});



	/** 完了. Fetch*/
function getComplete(id) {
	const data  = {
		    id: JSON.parse(id)};
	fetch('/complete', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	}).then(
		response => response.json()
	).then(res => {
				/* 　　セレクタもVue化できたら　　*/
				obj.textContent = "未完了";
				obj.setAttribute('href','/incomplete/' + global );
				obj.classList.remove('btn-complete','btn-dark');
				obj.classList.add('btn-incomplete','btn-outline-dark');
				obj.closest("tr");
				obj.parentNode.parentElement.parentElement.parentElement.classList.add('inComp');
				obj.parentNode.parentNode.parentNode.parentNode.firstElementChild.classList.remove('btn-outline-warning');
				obj.parentNode.parentNode.parentNode.parentNode.firstElementChild.classList.add('btn-lg');
				 /* 　　　アニメーション処理　　　Vueトランジション使いたい 
				obj.classList.toggle('swing');
				//obj.parentNode.parentElement.parentElement.parentElement.classList.add('animated' );
				obj.parentNode.parentElement.parentElement.parentElement.classList.toggle('bounce');　　　*/

	}).catch(error => {
		alert('ユーザー削除に失敗しました');
	});
}

/** 未完了. Fecth*/
function getInComplete(id) {
	const data  = {
		    id: JSON.parse(id)};
	fetch('/incomplete', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	}).then(
		response => response.json()
	).then(res => {
				obj.textContent = "完了";
				obj.setAttribute('href','/complete/' + global );
				obj.classList.remove('btn-incomplete','btn-outline-dark');
				obj.classList.add('btn-complete','btn-dark');
				obj.closest("tr");
				obj.parentNode.parentElement.parentElement.parentElement.classList.remove('inComp');
				obj.parentNode.parentNode.parentNode.parentNode.firstElementChild.classList.remove('btn-lg');
				obj.parentNode.parentNode.parentNode.parentNode.firstElementChild.classList.add('btn-outline-warning');
				 /** アニメーション処理*/

	}).catch(error => {
		alert('ユーザー削除に失敗しました');
	});
}
};



