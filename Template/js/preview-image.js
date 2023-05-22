let inputFile = document.querySelector(".photo-article");

if (inputFile === null) {
    inputFile = document.querySelector(".form-photo-profil");
}

inputFile.addEventListener("change", previewImage);

function previewImage(event) {
    let input = event.target;
    let imagePreview = document.querySelector('.image-preview');

    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
        imagePreview.setAttribute('src', e.target.result);
        imagePreview.style.display = 'block';
    };

    reader.readAsDataURL(input.files[0]);
    }
}