package chapter01;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedQuickUnion {

    /**
     * 记录连通分量的下一个分量的index：
     * 初始化时，p指向自身p；
     * 合并分量时，p指向下一个分量q的index，而q指向自身；
     */
    private int[] id;

    private int[] weight;

    /**
     * 分组数量
     */
    private int count;

    /**
     * 初始化规模
     * @param scale 规模
     */
    public WeightedQuickUnion(int scale) {
        id = new int[scale];
        weight = new int[scale];
        count = scale;
        // 初始化时，每个对象单独为一组，用自身的值作为组ID，链接自身
        for(int i = 0; i < scale; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }

    /**
     * find(p):
     * 1. 找出p的头部，循环直到头部；
     * 2. 头部为r。
     * p -> q -> r
     *           ↺
     */
    public int find(int p) {
        while (p != id[p]) {
            // 路径压缩
            // 路径压缩
            // id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    /**
     * union(q, s):
     * 1. 找出q的头部，找出s的头部；
     * 2. 分别是r和s；
     * 3. r指向s。
     * p -> q -> r   s  |  p -> q -> r -> s
     *           ↺   ↺  |                 ↺
     */
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }

        if (weight[pId] < weight[qId]) {
            id[pId] = qId;
            weight[qId] += weight[pId];
        } else {
            id[qId] = pId;
            weight[pId] += weight[qId];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}

class WeightedQuickUnionTestCase {
    public static void main(String[] args) {
        int scale = 100000;
        Date d1 = new Date();
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(scale);
        for (int i = 0; i < scale; i++) {
            weightedQuickUnion.union(
                    ThreadLocalRandom.current().nextInt(0, scale),
                    ThreadLocalRandom.current().nextInt(0, scale)
            );
        }
        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }
}
