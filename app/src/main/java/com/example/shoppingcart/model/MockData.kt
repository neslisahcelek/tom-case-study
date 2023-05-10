package com.example.shoppingcart.model

import java.util.Calendar

object MockData {
 object MockItem {
     var Item1:Item = Item("1","Mobile Phone","https://cdn.vatanbilgisayar.com/Upload/PRODUCT/apple/thumb/135211-1-5_large.jpg",10.0,1,"technology","The latest smartphone with a sleek design and cutting-edge features, offering a seamless user experience and high-performance capabilities.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item2:Item = Item("2","Laptop","https://cdn.evkur.com.tr/c/Product/thumbs/lenovo-ideapad3-1_ns95yr_500.jpg",20.0,1,"technology","Enhance your creativity with this versatile laptop, offering a vibrant touchscreen display, powerful graphics, and a range of multimedia editing tools.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item3:Item = Item("3","Headphones","https://m.media-amazon.com/images/I/51wdcQPZmnL._AC_SY355_.jpg",30.0,1,"technology","Enjoy superior comfort and noise isolation with these ergonomically designed headphones, allowing you to escape into your own world of music without distractions.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item4:Item = Item("4","Cleaner","https://productimages.hepsiburada.net/s/50/550/11017474310194.jpg/format:webp",40.0,1,"technology","Effortlessly keep your home clean and dust-free with this powerful and versatile vacuum cleaner, featuring strong suction power and multiple attachments for various surfaces.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item5:Item = Item("5","Tripod","https://productimages.hepsiburada.net/s/189/550/110000154758969.jpg/format:webp",50.0,1,"technology","Take your photography to the next level with this lightweight and portable tripod, perfect for travel and outdoor adventures, providing stability and flexibility in any situation.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item6:Item = Item("6","Hdmi Cable","https://productimages.hepsiburada.net/s/23/550/10062957641778.jpg/format:webp",60.0,1,"technology","Connect your devices with this high-speed HDMI cable, delivering crystal-clear audio and video signals for a seamless and immersive entertainment experience.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item7:Item = Item("7","Radio","https://productimages.hepsiburada.net/s/135/550/110000086174876.jpg/format:webp",70.0,1,"technology","Tune in to your favorite stations with this portable AM/FM radio, offering clear reception, compact design, and convenient battery-powered operation for on-the-go listening.",Calendar.getInstance().time,Calendar.getInstance().time)
     var Item8:Item = Item("8","Printer","https://productimages.hepsiburada.net/s/90/550/110000033268386.jpg/format:webp",80.0,1,"technology","Effortlessly print, scan, and copy with this versatile all-in-one printer, offering convenient wireless connectivity, intuitive controls, and high-quality output.",Calendar.getInstance().time,Calendar.getInstance().time)
 }

 object MockCoupon {
  val coupon1: Coupon = Coupon("1", 2, 10.0,20.0, "Get a 20% discount on your next order. Add the items to your cart and apply the coupon code to enjoy instant savings.")
  val coupon2: Coupon = Coupon("2", 2, 20.0,5.0, "Enjoy ₺5 off your purchase instantly! Add the items to your cart and get a discount. Start saving now with this hassle-free offer!")
  val coupon3: Coupon = Coupon( "3", 2, 30.0,15.0, "Get a 15% discount on your next order. Add the items to your cart and apply the coupon code to enjoy instant savings.")
  val coupon4: Coupon = Coupon("4", 1, 40.0,10.0,"Enjoy ₺10 off your purchase instantly! Add the items to your cart and get a discount. Start saving now with this hassle-free offer!")
  val coupon5: Coupon = Coupon("5", 1, 50.0,20.0,"Enjoy ₺20 off your purchase instantly! Add the items to your cart and get a discount. Start saving now with this hassle-free offer!")
  val coupon6: Coupon = Coupon("6", 1, 60.0,30.0,"Enjoy ₺30 off your purchase instantly! Add the items to your cart and get a discount. Start saving now with this hassle-free offer!")

  val couponList:ArrayList<Coupon> = arrayListOf( coupon5, coupon4, coupon6, coupon2)

 }
 object MockCart {
  val cartItemList = arrayListOf<Item>()
  var cart = Cart("0001","888",cartItemList, null ,0.0,0.0)

 }

 object MockItemList{
    val ItemList = arrayListOf<Item>(
     MockItem.Item1,
     MockItem.Item2,
     MockItem.Item3,
     MockItem.Item4,
     MockItem.Item5,
     MockItem.Item6,
     MockItem.Item7,
     MockItem.Item8
    )
 }

}