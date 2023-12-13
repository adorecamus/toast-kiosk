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
    val menuList = ArrayList<Pair<Menu, ArrayList<Menu>>>()

    val toast = Menu("TOAST", "한 손에 담긴 든든한 한 끼, 한 입에 퍼지는 미소")
    val toastList = ArrayList<Menu>()
    toastList.add(DeepCheeseBaconPotato("Deep Cheese Bacon Potato", 4900, "해시브라운과 베이컨,딥치즈 소스의 매력이 더해진 토스트에요."))
    toastList.add(HoneyGarlicHamCheese("Honey Garlic Ham Cheese", 3600, "허니갈릭 소스의 풍미가 매력적인 토스트에요."))
    toastList.add(MozzarellaSweetPotato("Mozzarella Sweet Potato", 4500, "매쉬드 고구마와 아이올리소스로 깊은 달콤함을 선물해요."))
    toastList.add(CornCheese("Corn Cheese", 4300, "콘버터 소스와 시즈닝으로 완성된 특별한 토스트에요."))
    toastList.add(HamSpecial("Ham Special", 3500, "햄치즈 토스트와 양배추의 조화가 돋보이는 토스트에요."))
    menuList.add(Pair(toast, toastList))

    val drinks = Menu("DRINK", "매장에서 직접 만드는 음료")
    val drinkList = ArrayList<Menu>()
    drinkList.add(KiwiJuice("Kiwi Juice", 3200, "상큼함 가득, 갈아 만든 키위주스"))
    drinkList.add(StrawberryBananaJuice("Strawberry Banana Juice", 3600, "딸기와 바나나가 만나 달콤함이 두배"))
    drinkList.add(StrawberryMilkShake("Strawberry Milk Shake", 3800, "입안 가득 퍼지는 새콤달콤함, 딸기쉐이크"))
    drinkList.add(IcedChoco("Iced Choco", 3300, "깊은 달콤함이 가득한 아이스 초코"))
    menuList.add(Pair(drinks, drinkList))

    while (true) {
        println("\"ISAAC TOAST 에 오신걸 환영합니다.\"")
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        while (true) {
            println("[ ISAAC MENU ]")
            for (i in menuList.indices) {
                print("${i + 1}. ")
                menuList[i].first.displayInfo()
            }
            var select: Int
            while (true) {
                try {
                    select = readln().toInt()
                    if (select < 1 || select > menuList.size) {
                        println("잘못된 번호를 입력했어요. 다시 입력해주세요.")
                    } else {
                        break
                    }
                } catch (e: Exception) {
                    println("숫자를 입력해주세요.")
                }
            }
            println("[ ${menuList[select - 1].first.name} MENU ]")
            val detailedMenuList = menuList[select - 1].second
            for (i in detailedMenuList.indices) {
                print("${i + 1}. ")
                detailedMenuList[i].displayInfo()
            }
            println("0. 뒤로가기")
            var order = false
            var detailedMenuSelect: Int
            while (true) {
                try {
                    val input = readln()
                    detailedMenuSelect = input.toInt()
                    if (detailedMenuSelect > detailedMenuList.size) {
                        println("잘못된 번호를 입력했어요. 다시 입력해주세요.")
                    } else if (detailedMenuSelect == 0) {
                        break
                    } else {
                        order = true
                        break
                    }
                } catch (e: Exception) {
                    println("숫자를 입력해주세요.")
                }
            }
            if (!order) {
                continue
            }
            print("\"")
            detailedMenuList[detailedMenuSelect - 1].displayInfo()
            print("\"")
            println("위 메뉴를 장바구니에 추가하시겠습니까?")
            // 장바구니 기능 구현 예정
        }
    }
}