$(document).ready(function(){

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/product",
      })
        .done(function( result ) {
          // $('#new-arrival').preappend('<h1></h1>')  Nối thêm chuỗi html động vào
          var htmlItem = ''

          var data = result.data
          for(i = 0; i<data.length; i++){
                var dataJson = JSON.stringify(data[i])
                htmlItem += `<div class="col-md-6 col-lg-3 my-4">
                <div class="product-item">
                  <div class="image-holder" style="width: 100%; height: 100%;">
                    <img src="${data[i].image}" alt="Books" class="product-image img-fluid">
                  </div>
                  <div class="cart-concern">
                    <div class="cart-button d-flex justify-content-between align-items-center">
                      <a data-product='${dataJson}' href="#" class="btn-cart btn-wrap cart-link d-flex align-items-center text-capitalize fs-6 ">add to cart <i
                          class="icon icon-arrow-io pe-1"></i>
                      </a>
                      <a href="single-product.html" class="view-btn">
                        <i class="icon icon-screen-full"></i>
                      </a>
                      <a href="#" class="wishlist-btn">
                        <i class="icon icon-heart"></i>
                      </a>
                    </div>
                  </div>
                  <div class="product-detail d-flex justify-content-between align-items-center mt-4">
                    <h4 class="product-title mb-0">
                      <a href="single-product.html">${data[i].tensp}</a>
                    </h4>
                    <p class="m-0 fs-5 fw-normal">$ ${data[i].gia}</p>
                  </div>
                </div>
              </div>`
          }

          

          $('#list-product').append(htmlItem)

          console.log(result);
        })

        var carts = []
        var dataCart = localStorage.getItem('cart')
        if(dataCart){
          carts = JSON.parse(dataCart)
        }

        console.log("Kiểm tra",carts)

        $('body').on('click','.btn-cart',function(){
          var isProductExistInCart = false
          var dataProduct = $(this).attr('data-product')
          var data = JSON.parse(dataProduct)

          for(i = 0; i < carts.length; i++){
            if(data.id == carts[i].id){
              carts[i].soluong += 1
              isProductExistInCart = true
            }
          }

          if(!isProductExistInCart){
            data.soluong = 1
            carts.push(data)
          }
          
          var dataJsonCart =JSON.stringify(carts)
          localStorage.setItem('cart',dataJsonCart)
          
          console.log(carts)
        })

        carts.forEach(function(item){
          var html = `<li class="list-group-item d-flex justify-content-between lh-sm">
                              <div>
                                <h6 class="my-0">${item.tensp}</h6>
                                <small class="text-body-secondary">Brief description</small>
                              </div>  
                              <span class="text-body-secondary">${item.gia}</span>
                          </li>`
            $('.container-cart').append(html)
          })
})
        