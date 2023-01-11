const fetchForm = document.querySelector('.inputCF');
const btn = document.querySelector('.btn');
const url = 'gen.php';

const postFetch = () => {
	
	show_loading();

    let formData = new FormData(fetchForm);
    for (let value of formData.entries()) {
        console.log(value);
    }

    fetch(url, {
        method: 'POST',
        body: formData
    }).then((response) => {
        if(!response.ok) {
            console.log('error!');
        } 
        console.log('ok!!!');
        return response.json();
    }).then((data)  => {
		document.querySelector('#genTitle').innerHTML = data['title'];
		document.querySelector('#genNote').innerHTML = data['note'];
		hide_loading();
        console.log(data);
    }).catch((error) => {
        console.log(error);
    });
};

btn.addEventListener('click', postFetch, false);
