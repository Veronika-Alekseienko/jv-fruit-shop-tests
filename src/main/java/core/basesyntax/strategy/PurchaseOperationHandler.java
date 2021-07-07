package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.dto.FruitDto;
import core.basesyntax.model.Fruit;

public class PurchaseOperationHandler implements OperationsHandler {
    @Override
    public int apply(FruitDto fruitDto) {
        Fruit fruit = new Fruit(fruitDto.getName());
        int currentQuantity = Storage.storage.getOrDefault(fruit, 0);
        int newQuantity = currentQuantity - fruitDto.getQuantity();
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough fruits in storage");
        }
        Storage.storage.put(fruit, newQuantity);
        return newQuantity;
    }
}