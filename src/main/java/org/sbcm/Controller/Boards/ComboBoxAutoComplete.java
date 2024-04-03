package org.sbcm.Controller.Boards;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;

import java.util.Comparator;

public class ComboBoxAutoComplete<T> {

    public ComboBoxAutoComplete(ComboBox<T> comboBox) {
        ObservableList<T> items = comboBox.getItems();

        comboBox.setOnKeyReleased(event -> {
            if (!comboBox.isShowing()) {
                comboBox.show();
            }

            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                return;
            }

            String input = comboBox.getEditor().getText();
            if (input == null || input.isEmpty()) {
                return;
            }

            items.sort(Comparator.comparingInt(item -> getMatchScore(item.toString(), input)));

            for (int i = 0; i < items.size(); i++) {
                T item = items.get(i);
                String itemText = item.toString().toLowerCase();
                if (itemText.startsWith(input.toLowerCase())) {
                    comboBox.getSelectionModel().select(item);
                    comboBox.getEditor().setText(item.toString());
                    comboBox.getEditor().positionCaret(input.length());
                    return;
                }
            }
        });

        comboBox.setOnMouseClicked(event -> {
            if (!comboBox.isShowing()) {
                comboBox.show();
            }
        });
    }

    private int getMatchScore(String itemText, String input) {
        if (itemText.equalsIgnoreCase(input)) {
            return Integer.MAX_VALUE;
        } else if (itemText.toLowerCase().startsWith(input.toLowerCase())) {
            return itemText.length();
        } else {
            return 0;
        }
    }

}
