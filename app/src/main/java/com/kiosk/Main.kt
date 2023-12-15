package com.kiosk

import kotlinx.coroutines.delay

val menus = ArrayList<Menu>()
val cart = ArrayList<Item>()
val account = Account()

suspend fun main() {
    getAllMenus()
    account.deposit((15000..25000).random().toLong())

    while (true) {
        println("\"ISAAC TOAST 에 오신걸 환영합니다.\"")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        var isHome = false
        while (!isHome) {
            // 메뉴 표시
            displayMenus()
            // 메뉴 선택
            val selectedMenuNumber: Int = selectNumberInRange(1, menus.size)
            val selectedMenu = menus[selectedMenuNumber - 1]
            val menuName = selectedMenu!!.name
            while (true) {
                // 주문 메뉴일 경우
                if (menuName == "ORDER") {
                    var totalPrice: Int = 0
                    println("아래와 같이 주문하시겠습니까?\n")
                    println("[ ORDERS ]")
                    for (item in cart) {
                        totalPrice += item.price
                        item.displayInfo()
                        println()
                    }
                    println("\n[ Total ]")
                    println("W ${totalPrice.toDouble() / 1000}\n")
                    println("1. 주문       2. 메뉴판")
                    val selectedNumber = selectNumberInRange(1, 2)
                    if (selectedNumber == 1) {
                        if (!account.withdraw((totalPrice).toLong())) {
                            val balance = account.getBalance()
                            println("현재 잔액은 ${balance}원으로 ${totalPrice - balance}원이 부족해 주문할 수 없습니다.\n")
                        } else {
                            println("결제를 완료했습니다.")
                        }
                        isHome = true
                    }
                    break
                }
                // 주문 취소 메뉴일 경우
                else if (menuName == "CANCEL") {
                    cart.clear()
                    isHome = true
                    break
                }
                // 아이템 선택하는 메뉴일 경우
                println("[ ${menuName} MENU ]")
                val items = selectedMenu.items
                for (i in items.indices) {
                    print("${i + 1}. ")
                    items[i].displayInfo()
                    println()
                }
                println("0. 뒤로가기")
                // 메뉴 아이템 선택
                val selectedItemNumber = selectNumberInRange(0, items.size)
                if (selectedItemNumber == 0) {
                    break
                }
                val selectedItem = items[selectedItemNumber - 1]
                print("\"")
                selectedItem.displayInfo()
                println("\"")
                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                println("1. 확인       2. 취소")
                // 장바구니 추가 선택
                val cartSelect = selectNumberInRange(1, 2)
                if (cartSelect == 1) {
                    cart.add(selectedItem)
                    println("${selectedItem.name} 장바구니에 추가되었습니다.\n")
                    break
                }

            }
        }
    }
}

suspend fun selectNumberInRange(start: Int, end: Int): Int {
    while (true) {
        try {
            val number = readln().toInt()
            if (number < start || number > end) {
                println("잘못된 번호를 입력했어요. 다시 입력해주세요.")
            } else {
                // 숫자 선택할 때마다 3초 지연
                delay(3000)
                return number
            }
        } catch (e: Exception) {
            println("숫자를 입력해주세요.")
        }
    }
}

fun getAllMenus() {
    val toast = Menu("TOASTS", "한 손에 담긴 든든한 한 끼, 한 입에 퍼지는 미소")
    val toastItems = toast.items
    toastItems.add(Item("Deep Cheese Bacon Potato", 4900, "해시브라운과 베이컨,딥치즈 소스의 매력이 더해진 토스트에요."))
    toastItems.add(Item("Honey Garlic Ham Cheese", 3600, "허니갈릭 소스의 풍미가 매력적인 토스트에요."))
    toastItems.add(Item("Mozzarella Sweet Potato", 4500, "매쉬드 고구마와 아이올리소스로 깊은 달콤함을 선물해요."))
    toastItems.add(Item("Corn Cheese", 4300, "콘버터 소스와 시즈닝으로 완성된 특별한 토스트에요."))
    toastItems.add(Item("Ham Special", 3500, "햄치즈 토스트와 양배추의 조화가 돋보이는 토스트에요."))
    menus.add(toast)

    val drink = Menu("DRINKS", "매장에서 직접 만드는 음료")
    val drinkItems = drink.items
    drinkItems.add(Item("Kiwi Juice", 3200, "상큼함 가득, 갈아 만든 키위주스"))
    drinkItems.add(Item("Strawberry Banana Juice", 3600, "딸기와 바나나가 만나 달콤함이 두배"))
    drinkItems.add(Item("Strawberry Milk Shake", 3800, "입안 가득 퍼지는 새콤달콤함, 딸기쉐이크"))
    drinkItems.add(Item("Iced Choco", 3300, "깊은 달콤함이 가득한 아이스 초코"))
    menus.add(drink)

    menus.add(Menu("ORDER", "장바구니를 확인 후 주문합니다."))
    menus.add(Menu("CANCEL", "진행 중인 주문을 취소합니다."))
}

fun displayMenus() {
    println("[ ISAAC MENU ]")
    for (i in menus.indices) {
        if (menus[i].name == "ORDER") {
            println("\n[ ORDER MENU ]")
        }
        print("${i + 1}. ")
        menus[i].displayInfo()
    }
}