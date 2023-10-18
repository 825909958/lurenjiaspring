package usuallyalgorithm.treetraverse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeEntity {
    TreeEntity left, right;
    Integer value;

    TreeEntity(Integer value) {
        this.value = value;
    }
}
