var mainZone = document.getElementById("mainZone");
var itemDetailContainer = document.getElementById("itemDetailContainer");
var addItemContainer = document.getElementById("addItemContainer");
var itemMngContainer = document.getElementById("itemMngContainer");


$(".menubar").on("click", function () {

    if(!mainZone){
    }else{
        if (mainZone.style.display === "block") {
            mainZone.style.display = "none";
        } else {
            mainZone.style.display = "block";
        }
    }

    if(!itemMngContainer){
    }else{
        if (itemMngContainer.style.display === "flex") {
            itemMngContainer.style.display = "none";
        } else {
            itemMngContainer.style.display = "flex";
        }
    }

    if(!addItemContainer){
    }else{
        if (addItemContainer.style.display === "flex") {
            addItemContainer.style.display = "none";
        } else {
            addItemContainer.style.display = "flex";
        }
    }

    if(!itemDetailContainer){
    }else{
        if (itemDetailContainer.style.display === "block") {
            itemDetailContainer.style.display = "none";
        } else {
            itemDetailContainer.style.display = "block";
        }
    }

});

window.addEventListener("resize", function () {
    var width = window.innerWidth;

    if (width > 880) {
        if(!mainZone){
        }else{
                mainZone.style.display = "block";
        }

        if(!itemDetailContainer){
        }else{
                itemDetailContainer.style.display = "block";
        }
    }

});