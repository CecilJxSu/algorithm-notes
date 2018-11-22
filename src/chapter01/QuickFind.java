package chapter01;

/**
 * 同一分组，记录相同的分组ID
 */
public class QuickFind {

    public static void main(String[] args) {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(1, 3);
        quickFind.union(4, 5);
        quickFind.union(1, 4);
        System.out.println(quickFind.connected(3, 5));
        System.out.println(quickFind.count());
        System.out.println(quickFind.find(9));
    }

    /**
     * 记录无序对的分组ID，相同ID的对象为同一组
     */
    private int[] id;

    /**
     * 分组数量
     */
    private int count;

    /**
     * 初始化规模
     * @param scale 规模
     */
    public QuickFind(int scale) {
        id = new int[scale];
        count = scale;
        // 初始化时，每个对象单独为一组，用自身的值作为组ID
        for (int i = 0; i < scale; i++) {
            id[i] = i;
        }
    }

    /**
     * 判断两对象是否连通
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * 查询对象的组ID，运行时间规模为常数级别，
     * 因此该算法实现称为Quick-Find
     * @param p
     * @return
     */
    public int find(int p) {
        return id[p];
    }

    /**
     * 合并两对象为同一组，
     * 每次合并的运行时间规模为N，如果合并M对，则规模为平方级别
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        // 组ID一致，则已经为同一组，忽略
        if (pId == qId) {
            return;
        }
        // 将其中一组对象，全部设置为q的组ID，合并分组
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        // 合并后，分组减少
        count--;
    }

    /**
     * 获取分组数目
     * @return
     */
    public int count() {
        return count;
    }
}
