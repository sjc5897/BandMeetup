    var password = document.getElementById("pw");
    var passwordC = document.getElementById("pwC");
    var letter = document.getElementById("lower");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");
    var match = document.getElementById("match");

    password.onfocus = function(){
        document.getElementById("password_crit").style.display = "block";
        document.getElementById("password_crit").style.textAlign = "center";
        password.onclick = function(){
            document.getElementById("regForm").style.paddingBottom = "220px";
            document.getElementById("container-reg").style.minHeight = "150vh";
            document.getElementById("regWrapper").style.top = "45%";
        };
    };

    passwordC.onfocus = function(){
        document.getElementById("password_conf").style.display = "block";
        document.getElementById("password_conf").style.textAlign = "center";
    };
    password.onblur= function(){
        document.getElementById("password_crit").style.display = "none";
    };
    passwordC.onblur = function(){
        document.getElementById("password_conf").style.display = "none";
    };
    password.onkeyup = function(){
        var lowerCaseLetters = /[a-z]/g;
        if(password.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        }
        else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }
        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if(password.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        }
        else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }
        // Validate numbers
        var numbers = /[0-9]/g;
        if(password.value.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }
        // Validate length
        if(password.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    };

    passwordC.onkeyup = function () {
        if (passwordC.value === password.value) {
            match.classList.remove("invalid");
            match.classList.add("valid")
        } else {
            match.classList.remove("valid");
            match.classList.add("invalid")
        }
    }