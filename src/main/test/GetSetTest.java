import com.zwyue.entity.ShoppingCart;
import com.zwyue.record.Item;
import java.util.List;

public class GetSetTest {

    // 避免滥用getter、setter方法
    private static void shoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Item item = new Item("1","apple",2.0);
        Item item1 = new Item("1","apple",2.0);
        Item item2= new Item("1","apple",2.0);
        Item item4= new Item("2","banana",2.0);

        shoppingCart.add(item);
        shoppingCart.add(item2);
        shoppingCart.add(item4);
        shoppingCart.add(item1);


        shoppingCart.remove(item);
        List<Item> items = shoppingCart.getItems();


        // 此处会抛出异常，因为使用了 unmodifiableList
        // 而面向对象封装的定义是：通过访问权限控制，隐藏内部数据，外部仅能通过类提供的有限的接口访问、修改内部数据。
//        items.clear();
        System.out.println(shoppingCart);
    }

    public static void main(String[] args) {
        shoppingCart();
    }
}
