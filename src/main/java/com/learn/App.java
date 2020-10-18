package com.learn;

public class App {
    private static final String KEY = "key";
    public static void main(String[] args) {
        public List<Category> findAll() {
            Jedis jedis = JedisUtil.getJedis();
            Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
            List<Category> cs = null;
            if (categorys == null || categorys.size() == 0) {

                System.out.println("从数据库查询....");
                cs = categoryDao.findAll();
                for (int i = 0; i < cs.size(); i++) {

                    jedis.zadd("category", cs.get(i).getSno(), cs.get(i).getName(), cs.get(i).getAge());
                }
            } else {
                System.out.println("从redis中查询.....");

                cs = new ArrayList<Category>();
                for (Tuple tuple : categorys) {
                    Category category = new Category();
                    category.setCname(tuple.getElement());
                    category.setCid((int)tuple.getScore());
                    cs.add(category);

                }
            }

            return cs;
        }
    }


    }
}
