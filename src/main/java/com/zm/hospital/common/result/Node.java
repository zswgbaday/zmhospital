package com.zm.hospital.common.result;

/**
 * 树的节点
 *
 * @param <T>
 */
public class Node<T> implements java.io.Serializable {

    private static final long serialVersionUID = 980682543891282923L;

    private boolean checked = false;//是否选中
    private int depth;//深度,第几级
    private T item;//绑定的特殊对象

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }


}
