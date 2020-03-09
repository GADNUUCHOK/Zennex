package com.example.zennex.ui.viewComponent;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.zennex.R;


/**
 * Класс для отображения фрагментов в активности.
 */
public abstract class SingleFragmentActivity
        extends AppCompatActivity {
    /**
     *
     * @return Абстрактный метод для создания Фрагмента. Служит для сокращения кода в программе.
     */
    protected abstract Fragment createFragment();                                                   // Абстрактный метод для для создания фрагмента
    /**
     * Метод для переопределения для возвращаемого макета
     * @return макет фрагмента
     */
    @LayoutRes
    private int getLayoutResId() {
        return R.layout.activity_main;                                                               // макет фрагмента
    }
    /**
     *
     * @param savedInstanceState служит для сохранения данных в пакете для того чтобы к ним можно
     *      * было вновь обратиться в случае необходимости
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            // Сохраняем текущее состояние компонентов экрана
        super.onCreate(savedInstanceState);                                                         // VM запускает код в дополнение к существующему коду в onCreate родительского класса
        setContentView(getLayoutResId());                                                           // Получаем ссылку на готовый Xml файл данного компонента


        FragmentManager fm = getSupportFragmentManager();                                           // Обращаемся к объекту FragmentManager, чтобы добавить фрагмент в активность в коде
        Fragment fragment = fm.findFragmentById(R.id.main_activity);                                // Сообщаем FragmentManager, где в активности должно находится представление фрагмента

        if (fragment == null) {                                                                     // Условие, если фрагмент пуст
            fragment = createFragment();                                                            // Тогда создаем новый фрагмент
            fm.beginTransaction()                                                                   // Создаем и возвращаем экземпляр FragmentTransaction
                    .add(R.id.main_activity, fragment)                                              // Создаем транзакцию фрагмента
                    .commit();                                                                      // Закрепляем транзакцию фрагмента
        }
    }
}
