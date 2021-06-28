document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          if (this.validateForm()) {
            this.currentStep++;
            this.updateForm();
          }
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          if (this.validateForm()) {
            this.currentStep--;
            this.updateForm();
          }
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation


      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      if(this.currentStep===5) {
        let arrHowManyWho = [...document.querySelectorAll(".summary--text")];
        const howMany = document.querySelector("#quantity").value
        const forWho = document.querySelector('input[name="institution"]:checked').value;
        const categories = [...document.querySelectorAll('input[name="categories"]:checked')];
        const street = document.querySelector('input[name="street"]').value;
        const city = document.querySelector('input[name="city"]').value;
        const zipCode = document.querySelector('input[name="zipCode"]').value;
        const phone = document.querySelector('input[name="phone"]').value;
        const pickUpDate = document.querySelector('input[name="pickUpDate"]').value;
        const pickUpTime = document.querySelector('input[name="pickUpTime"]').value;
        const moreInfo = document.querySelector('textarea[name="more_info"]').value;

        const categoriesText = categories.map(cat => cat.value).join(", ");
        arrHowManyWho[0].innerText=howMany + " worki: " + categoriesText;
        arrHowManyWho[1].innerText="Dla fundacji \"" + forWho + "\"";

        let arrAddress = [...document.querySelectorAll(".summary .form-section--column li")];
        arrAddress[0].innerText=street;
        arrAddress[1].innerText=city;
        arrAddress[2].innerText=zipCode;
        arrAddress[3].innerText=phone;
        arrAddress[4].innerText=pickUpDate;
        arrAddress[5].innerText=pickUpTime;
        arrAddress[6].innerText=moreInfo;

      }
      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

    }

    validateForm() {
      //check categories
      const categories = [...document.querySelectorAll('input[name="categories"]:checked')]
      if (categories.length===0) {
        alert("wybierz przynajmniej jedną kategorię!");
        return false;
      }
      //check quantity
      const quantity = document.querySelector("#quantity");
      if (this.currentStep===2 && (quantity.value>10 || quantity.value<1)) {
        alert("liczba worków powinna być z przedziału 1-10 ");
        return false;
      }

      //check institutions
      const institution = document.querySelector('input[name="institution"]:checked');
      if (this.currentStep===3 && institution===null) {
        alert("wybierz przynajmniej jedną instytucję!");
        return false;
      }

      //check address
      const street = document.querySelector('input[name="street"]').value;
      const city = document.querySelector('input[name="city"]').value;
      const zipCode = document.querySelector('input[name="zipCode"]').value;
      const phone = document.querySelector('input[name="phone"]').value;

      if (this.currentStep===4) {
        if (this.isEmpty(street) || this.isEmpty(city) || this.isEmpty(zipCode)) {
          alert("podaj kompletny adres!");
          return false;
        }

        if (!/[0-9]{2}-[0-9]{3}/.test(zipCode)) {
          alert("kod pocztowy powinien być w formacie xx-xxx");
          return false;
        }

        if (!/[0-9]{9}/.test(phone)) {
          alert("podaj numer telefonu w formacie 9cyfr!");
          return false
        }
      }

      //check date and time
      const pickUpDate = document.querySelector('input[name="pickUpDate"]').value;
      const pickUpTime = document.querySelector('input[name="pickUpTime"]').value;
      const dateNow = new Date();
      const datePickUpDate = new Date(pickUpDate);
      const pickUpHour = pickUpTime.split(":")[0]

      if (this.currentStep===4) {
        if (this.isEmpty(pickUpDate) || this.isEmpty(pickUpTime)) {
          alert("podaj datę i godzinę odbioru!");
          return false
        }
        if (datePickUpDate.getTime()<=dateNow.getTime()) {
          alert("data odbioru nie może być wcześniejsza niż na jutro!");
          return false
        }
        if (pickUpHour<8 || pickUpHour>20) {
          alert("godzina odbioru musi być między 8 a 20");
          return false
        }
      }

      return true;
    }

    isEmpty(str) {
      return !str.trim().length;
    }
  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
