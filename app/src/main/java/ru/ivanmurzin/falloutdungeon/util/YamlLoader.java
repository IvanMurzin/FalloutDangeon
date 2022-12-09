package ru.ivanmurzin.falloutdungeon.util;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.Nullable;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Armor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.ArmorType;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

@SuppressWarnings("unchecked")
public class YamlLoader {

    public final List<Armor> armorList = new LinkedList<>();

    public YamlLoader(Context context) {
        try {
            Map<String, Object> data = getYamlData(context);
            Objects.requireNonNull(data);
            List<Map<String, Object>> armorObjects = (List<Map<String, Object>>) data.get("armor");
            Objects.requireNonNull(armorObjects);
            for (Map<String, Object> armor : armorObjects) {
                Integer id = (Integer) armor.get("id");
                Objects.requireNonNull(id);
                String name = (String) armor.get("name");
                Objects.requireNonNull(name);
                Double defence = (Double) armor.get("defence");
                Objects.requireNonNull(defence);
                ArmorType type = ArmorType.getArmorTypeFromString((String) armor.get("type"));
                Map<String, Object> resistance = (Map<String, Object>) armor.get("resistance");
                Objects.requireNonNull(resistance);
                WeaponType resistanceType = WeaponType.getWeaponTypeFromString((String) resistance.get("type"));
                Double resistanceValue = (Double) resistance.get("value");
                Objects.requireNonNull(resistanceType);
                Objects.requireNonNull(resistanceValue);
                Pair<WeaponType, Double> resistancePair = new Pair<>(resistanceType, resistanceValue);
                armorList.add(new Armor(id, name, defence, type, resistancePair));
            }
            Log.i(Constants.TAG_I, armorObjects.toString());
        } catch (Exception e) {
            throw new YAMLException("Unable to parse data.yaml", e);
        }
    }

    @Nullable
    private Map<String, Object> getYamlData(Context context) {
        Yaml yaml = new Yaml();
        InputStream inputStream = context.getResources().openRawResource(R.raw.data);
        Object obj = yaml.load(inputStream);
        return (Map<String, Object>) obj;
    }

}
