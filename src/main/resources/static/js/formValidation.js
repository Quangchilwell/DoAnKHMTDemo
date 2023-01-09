const formElement = document.querySelector("#form-validation");
var message = {
  textRequired: "Vui lòng nhập trường này",
  textErrorEmail: "Email không hợp lệ",
  textErrorPhone: "Số điện thoại không hợp lệ",
  textInvalidPassword: "Mật khẩu phải có ít nhất 3 kí tự",
  textErrorPassword: "Mật khẩu nhập lại không hợp lệ",
};

var regex = {
  email: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
  phone: /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im,
};

if (formElement) {
  const inputElements = formElement.querySelectorAll(".form-control");
  const btnSubmit = formElement.querySelector(".form-submit");

  function checkValid(inputElement, formGroup, formError, message) {
    if (inputElement.value.trim() == "") {
      formError.innerText = message.textRequired;
      formGroup.classList.add("invalid");
    } else {
      formError.innerText = "";
      formGroup.classList.remove("invalid");
    }

    // Kiem tra cho email
    if (
      inputElement.value.trim() != "" &&
      inputElement.type === "email" &&
      !regex.email.test(inputElement.value)
    ) {
      formError.innerText = message.textErrorEmail;
      formGroup.classList.add("invalid");
    }

    // Kiem tra sdt
    if(inputElement.value.trim() != "" 
    && inputElement.name === 'phone' 
    && !regex.phone.test(inputElement.value)){
        formError.innerText = message.textErrorPhone;
        formGroup.classList.add("invalid");
    }

    // Kiem tra cho mat khau nhap vao
    if (inputElement.value.trim() != "" && inputElement.type === "password") {
      if (inputElement.name === 'password' && inputElement.value.length < 3) {
        formError.innerText = message.textInvalidPassword;
        formGroup.classList.add("invalid");
      }
    }

    return !formError.innerText
  }


  const password = document.querySelector('#password')
  inputElements.forEach((inputElement) => {
    var formGroup = inputElement.parentElement;
    var formError = formGroup.querySelector(".form-message");
    inputElement.onblur = function () {
      checkValid(inputElement, formGroup, formError, message)
      if(inputElement.name === 'password'){
        password.value = inputElement.value
      }
     
      if(inputElement.name === 'password_confirmation' && inputElement.value !== password.value){
        formError.innerText = message.textErrorPassword;
        formGroup.classList.add("invalid");
      }
    };

    inputElement.oninput = function () {
        formError.innerText = "";
        formGroup.classList.remove("invalid");
    };
  });

  var listValid = []
  btnSubmit.onclick = function (e) {
    e.preventDefault()
    inputElements.forEach((inputElement) => {
        var formGroup = inputElement.parentElement;
        var formError = formGroup.querySelector(".form-message");
        // checkValid(inputElement, formGroup, formError, message)
        listValid.push(checkValid(inputElement, formGroup, formError, message))
    });
   
    if(listValid.includes(false)){
        listValid = []
    }
    else{
        formElement.submit()
    }
  };
  
}
