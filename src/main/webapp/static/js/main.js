const laptopButton = document.querySelector("#laptop");
const tabletButton = document.querySelector("#tablet");

// tabletButton.addEventListener("click", e => {
//     fetch(`/homepage?category=${e.target.firstChild.data}`).then(r => r.json()).then(data => console.log(data));
// })





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