const API_BASE = 'http://localhost:8080/';
const productData = [];

function displayItem(product) {
    const main = document.getElementById('main');
    const tmpl = document.getElementById('product-template').content.cloneNode(true);
    tmpl.querySelector('h3').innerText = product.name;
    tmpl.querySelector('img').setAttribute("src", product.url);
    tmpl.querySelector('p').innerText = product.price;
    main.appendChild(tmpl);
}

function displayInventory(products) {
    if ('content' in document.createElement('template')) {
        for (let i = products.length - 1; i >= 0; i--) {
            productData.push(products[i]);
            displayItem(products[i]);
        }
    } else {
        console.error('Your browser does not support this content.');
    }
}

function loadAll() {
    fetch(API_BASE + 'products')
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            displayInventory(data);
        })
        .catch((err) => {
            console.error(err);
            alert('Failed to load site. Please try again later.');
        });   
}

document.addEventListener('DOMContentLoaded', () => {
    loadAll();
});
