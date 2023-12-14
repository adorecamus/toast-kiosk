package com.kiosk

import com.kiosk.item.Item
import com.kiosk.item.drink.IcedChoco
import com.kiosk.item.drink.KiwiJuice
import com.kiosk.item.drink.StrawberryBananaJuice
import com.kiosk.item.drink.StrawberryMilkShake
import com.kiosk.item.toast.CornCheese
import com.kiosk.item.toast.DeepCheeseBaconPotato
import com.kiosk.item.toast.HamSpecial
import com.kiosk.item.toast.HoneyGarlicHamCheese
import com.kiosk.item.toast.MozzarellaSweetPotato
import com.kiosk.menu.Drink
import com.kiosk.menu.Menu
import com.kiosk.menu.Toast

fun main() {
    val menus = getAllMenus()
    val cart = ArrayList<Item>()
    val account = Account()
    account.deposit((15000..25000).random().toLong())

    while (true) {
        println("\"ISAAC TOAST 에 오신걸 환영합니다.\"")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        var isHome = false
        while (!isHome) {
            // 메뉴 표시
            println("[ ISAAC MENU ]")
            for (i in menus.indices) {
                print("${i + 1}. ")
                menus[i].displayInfo()
            }
            var menuSize = menus.size
            var orderMenu: Int = 0
            var cancleMenu: Int = 0
            // 장바구니에 담겨 있는 경우
            if (cart.size > 0) {
                orderMenu = menuSize + 1
                cancleMenu = menuSize + 2
                menuSize += 2
                println("\n[ ORDER MENU ]")
                println(String.format("${orderMenu}. %-8s | %-12s", "ORDER", "장바구니를 확인 후 주문합니다."))
                println(String.format("${cancleMenu}. %-8s | %-12s", "CANCEL", "진행 중인 주문을 취소합니다."))
            }
            // 메뉴 선택
            val selectedMenuNumber: Int = numberInRange(1, menuSize)
            while (true) {
                // 주문
                if (selectedMenuNumber == orderMenu) {
                    var totalPrice: Int = 0
                    println("아래와 같이 주문하시겠습니까?\n")
                    println("[ ORDER ]")
                    for (item in cart) {
                        totalPrice += item.price
                        item.displayInfo()
                        println()
                    }
                    println("\n[ Total ]")
                    println("W ${totalPrice.toDouble() / 1000}\n")
                    println("1. 주문       2. 메뉴판")
                    val select = numberInRange(1, 2)
                    if (select == 1) {
                        isHome = true
                    }
                    break
                }
                // 주문 취소
                else if (selectedMenuNumber == cancleMenu) {
                    break
                }
                // 메뉴 아이템 표시
                println("[ ${menus[selectedMenuNumber - 1].name} MENU ]")
                val menuList = menus[selectedMenuNumber - 1].items
                for (i in menuList.indices) {
                    print("${i + 1}. ")
                    menuList[i].displayInfo()
                    println()
                }
                println("0. 뒤로가기")
                // 메뉴 아이템 선택
                val selectedItemNumber = numberInRange(0, menuList.size)
                if (selectedItemNumber == 0) {
                    break
                }
                val selectedItem = menuList[selectedItemNumber - 1]
                print("\"")
                selectedItem.displayInfo()
                println("\"")
                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                println("1. 확인       2. 취소")
                // 장바구니 추가 선택
                val cartSelect = numberInRange(1, 2)
                if (cartSelect == 1) {
                    cart.add(selectedItem)
                    println("${selectedItem.name} 장바구니에 추가되었습니다.\n")
                    break
                }
            }
        }
    }
}

fun numberInRange(start: Int, end: Int): Int {
    while (true) {
        try {
            val number = readln().toInt()
            if (number < start || number > end) {
                println("잘못된 번호를 입력했어요. 다시 입력해주세요.")
            } else {
                return number
            }
        } catch (e: Exception) {
            println("숫자를 입력해주세요.")
        }
    }
}

fun getAllMenus(): ArrayList<Menu> {
    val menus = ArrayList<Menu>()

    val toast = Toast("TOASTS", "한 손에 담긴 든든한 한 끼, 한 입에 퍼지는 미소")
    val toastItems = toast.items
    toastItems.add(DeepCheeseBaconPotato("Deep Cheese Bacon Potato", 4900, "해시브라운과 베이컨,딥치즈 소스의 매력이 더해진 토스트에요."))
    toastItems.add(HoneyGarlicHamCheese("Honey Garlic Ham Cheese", 3600, "허니갈릭 소스의 풍미가 매력적인 토스트에요."))
    toastItems.add(MozzarellaSweetPotato("Mozzarella Sweet Potato", 4500, "매쉬드 고구마와 아이올리소스로 깊은 달콤함을 선물해요."))
    toastItems.add(CornCheese("Corn Cheese", 4300, "콘버터 소스와 시즈닝으로 완성된 특별한 토스트에요."))
    toastItems.add(HamSpecial("Ham Special", 3500, "햄치즈 토스트와 양배추의 조화가 돋보이는 토스트에요."))
    menus.add(toast)

    val drink = Drink("DRINKS", "매장에서 직접 만드는 음료")
    val drinkItems = drink.items
    drinkItems.add(KiwiJuice("Kiwi Juice", 3200, "상큼함 가득, 갈아 만든 키위주스"))
    drinkItems.add(StrawberryBananaJuice("Strawberry Banana Juice", 3600, "딸기와 바나나가 만나 달콤함이 두배"))
    drinkItems.add(StrawberryMilkShake("Strawberry Milk Shake", 3800, "입안 가득 퍼지는 새콤달콤함, 딸기쉐이크"))
    drinkItems.add(IcedChoco("Iced Choco", 3300, "깊은 달콤함이 가득한 아이스 초코"))
    menus.add(drink)

    return menus
}