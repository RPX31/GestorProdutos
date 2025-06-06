document.addEventListener('DOMContentLoaded', function () {
  // Modal Marca
  const btnAbrirMarca = document.getElementById("abrirModalMarca");
  const btnFecharMarca = document.getElementById("fecharModalMarca");
  const modalMarca = document.getElementById("modalMarca");

  if (btnAbrirMarca && btnFecharMarca && modalMarca) {
    btnAbrirMarca.addEventListener("click", () => modalMarca.classList.add("active"));
    btnFecharMarca.addEventListener("click", () => modalMarca.classList.remove("active"));
  }

  // Modal Categoria
  const btnAbrirCategoria = document.getElementById("abrirModalCategoria");
  const btnFecharCategoria = document.getElementById("fecharModalCategoria");
  const modalCategoria = document.getElementById("modalCategoria");

  if (btnAbrirCategoria && btnFecharCategoria && modalCategoria) {
    btnAbrirCategoria.addEventListener("click", () => modalCategoria.classList.add("active"));
    btnFecharCategoria.addEventListener("click", () => modalCategoria.classList.remove("active"));
  }

  // Modal Produto (novo)
  const btnAbrirProduto = document.getElementById("abrirModalProduto");
  const btnFecharProduto = document.getElementById("fecharModalProduto");
  const modalProduto = document.getElementById("modalProduto");

  if (btnAbrirProduto && btnFecharProduto && modalProduto) {
    btnAbrirProduto.addEventListener("click", () => modalProduto.classList.add("active"));
    btnFecharProduto.addEventListener("click", () => modalProduto.classList.remove("active"));
  }
});

// Carrossel de tabelas
document.addEventListener('DOMContentLoaded', function () {
  const slides = document.querySelectorAll('.carousel-slide');
  const leftArrow = document.querySelector('.left-arrow');
  const rightArrow = document.querySelector('.right-arrow');
  let currentIndex = 0;

  function updateSlides() {
    slides.forEach((slide, i) => {
      slide.classList.toggle('active', i === currentIndex);
    });
  }

  if (leftArrow && rightArrow && slides.length > 0) {
    leftArrow.addEventListener('click', () => {
      currentIndex = (currentIndex - 1 + slides.length) % slides.length;
      updateSlides();
    });

    rightArrow.addEventListener('click', () => {
      currentIndex = (currentIndex + 1) % slides.length;
      updateSlides();
    });
  }

  updateSlides(); // Inicializa
});

