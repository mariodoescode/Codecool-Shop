const addToCartButtons = document.querySelectorAll(".buttonAddToCart");
console.dir(addToCartButtons);


addToCartButtons.forEach((addButton) => addButton.addEventListener('click', (event) => {
        console.log(event);
        const url = `/shopping-cart?id=${event.target.dataset.productId}`;
        // fetchProduct(url);
        console.log(fetchProduct(url));
    })
)

async function fetchProduct(url) {
    const response = await fetch(url);
    return response.json();
}


function myFunctionSup() {
    document.getElementById("myDropdownSup").classList.toggle("show");
}

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        let dropdowns = document.getElementsByClassName("dropdown-content");
        let i;
        for (i = 0; i < dropdowns.length; i++) {
            let openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}



