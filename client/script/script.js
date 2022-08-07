const API_BASE = 'http://localhost:8080/';
const productData = [];
const isLoggedIn = false;

function displayItem(product) {
    const main = document.getElementById('inventory');
    const tmpl = document.getElementById('product-template').content.cloneNode(true);
    tmpl.querySelector('h3').innerText = product.productName;
    tmpl.querySelector('img').setAttribute("src", product.url);
    tmpl.querySelector('p').innerText = product.productPrice;
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

function showLoginForm() {
    const loginForm = document.getElementById('log-in-form');
    loginForm.style.display = 'block';
}

function logIn() {
    event.preventDefault();
    const loginForm = document.getElementById('log-in-form');
    loginForm.style.display = 'none';
    const logInButton = document.getElementById('log-in-button');
    logInButton.removeEventListener('click', showLoginForm);
    logInButton.innerText = 'Jonathan Ward';
    isLoggedIn = true;
}

document.addEventListener('DOMContentLoaded', () => {
    loadAll();
    const logInButton = document.getElementById('log-in-button');
    logInButton.addEventListener('click', showLoginForm);
});
