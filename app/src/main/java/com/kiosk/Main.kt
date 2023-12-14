package com.kiosk

import com.kiosk.menu.Menu
import com.kiosk.menu.toast.DeepCheeseBaconPotato
import com.kiosk.menu.toast.HamSpecial
import com.kiosk.menu.toast.HoneyGarlicHamCheese
import com.kiosk.menu.drink.IcedChoco
import com.kiosk.menu.drink.KiwiJuice
import com.kiosk.menu.drink.StrawberryBananaJuice
import com.kiosk.menu.drink.StrawberryMilkShake
import com.kiosk.menu.toast.CornCheese
import com.kiosk.menu.toast.MozzarellaSweetPotato

fun main() {
    val menu = getAllMenuList()
    val cart = ArrayList<Menu>()
    val account = Account()
    account.deposit((15000..25000).random().toLong())

    while (true) {
        println("\"ISAAC TOAST 에 오신걸 환영합니다.\"")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        var isHome = false
        while (!isHome) {
            // 상위 메뉴 표시
            println("[ ISAAC MENU ]")
            for (i in menu.indices) {
                print("${i + 1}. ")
                menu[i].displayInfo()
            }
            var menuSize = menu.size
            var orderSelect: Int = 0
            var cancleSelect: Int = 0
            // 장바구니에 담겨 있는 경우
            if (cart.size > 0) {
                orderSelect = menuSize + 1
                cancleSelect = menuSize + 2
                menuSize += 2
                println("\n[ ORDER MENU ]")
                println(String.format("${orderSelect}. %-8s | %-12s", "ORDER", "장바구니를 확인 후 주문합니다."))
                println(String.format("${cancleSelect}. %-8s | %-12s", "CANCEL", "진행 중인 주문을 취소합니다."))
            }
            // 상위 메뉴 선택
            val select = numberInRange(1, menuSize)
            while (true) {
                // 주문
                if (select == orderSelect) {
                    var totalPrice: Int = 0
                    println("아래와 같이 주문하시겠습니까?\n")
                    println("[ ORDER ]")
                    for (item in cart) {
//                        totalPrice += item.price
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
                else if (select == cancleSelect) {
                    break
                }
                // 하위 메뉴 표시
                println("[ ${menu[select - 1].name} MENU ]")
                val menuList = menu[select - 1].list
                for (i in menuList.indices) {
                    print("${i + 1}. ")
                    menuList[i].displayInfo()
                    println()
                }
                println("0. 뒤로가기")
                // 하위 메뉴 선택
                val menuSelect = numberInRange(0, menuList.size)
                if (menuSelect == 0) {
                    break
                }
                val selectedMenu = menuList[menuSelect - 1]
                print("\"")
                selectedMenu.displayInfo()
                println("\"")
                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                println("1. 확인       2. 취소")
                // 장바구니 추가 선택
                val cartSelect = numberInRange(1, 2)
                if (cartSelect == 1) {
                    cart.add(selectedMenu)
                    println("${selectedMenu.name} 장바구니에 추가되었습니다.\n")
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

fun getAllMenuList(): ArrayList<Menu> {
    val menu = ArrayList<Menu>()

    val toast = Menu("TOAST", "한 손에 담긴 든든한 한 끼, 한 입에 퍼지는 미소")
    val toastList = toast.list
    toastList.add(DeepCheeseBaconPotato("Deep Cheese Bacon Potato", 4900, "해시브라운과 베이컨,딥치즈 소스의 매력이 더해진 토스트에요."))
    toastList.add(HoneyGarlicHamCheese("Honey Garlic Ham Cheese", 3600, "허니갈릭 소스의 풍미가 매력적인 토스트에요."))
    toastList.add(MozzarellaSweetPotato("Mozzarella Sweet Potato", 4500, "매쉬드 고구마와 아이올리소스로 깊은 달콤함을 선물해요."))
    toastList.add(CornCheese("Corn Cheese", 4300, "콘버터 소스와 시즈닝으로 완성된 특별한 토스트에요."))
    toastList.add(HamSpecial("Ham Special", 3500, "햄치즈 토스트와 양배추의 조화가 돋보이는 토스트에요."))
    menu.add(toast)

    val drinks = Menu("DRINK", "매장에서 직접 만드는 음료")
    val drinkList = drinks.list
    drinkList.add(KiwiJuice("Kiwi Juice", 3200, "상큼함 가득, 갈아 만든 키위주스"))
    drinkList.add(StrawberryBananaJuice("Strawberry Banana Juice", 3600, "딸기와 바나나가 만나 달콤함이 두배"))
    drinkList.add(StrawberryMilkShake("Strawberry Milk Shake", 3800, "입안 가득 퍼지는 새콤달콤함, 딸기쉐이크"))
    drinkList.add(IcedChoco("Iced Choco", 3300, "깊은 달콤함이 가득한 아이스 초코"))
    menu.add(drinks)

    return menu
}