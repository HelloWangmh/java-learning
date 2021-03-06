package wang.mh.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 明辉 on 2017/7/20.
 */
public class KeywordFilter {


    public static void main(String[] args) throws IOException
    {
        Date date = new Date();
        KeywordFilter filter = KeywordFilter.getInstance();
        String txt = "王王";
        boolean boo = filter.isContentKeyWords(txt);
        System.out.println(boo);
        List<String> set = filter.getTxtKeyWords(txt);
        System.out.println("包含的敏感词如下：" + set);
        Date date2 = new Date();
        float ss = date2.getTime() - date.getTime();
        System.out.println(ss + "毫秒");
        System.out.println(txt);
    }


    private static KeywordFilter instance = null;
    /**
     * 直接禁止的
     */
    private HashMap keysMap = new HashMap();
    private int matchType = 1;            // 1:最小长度匹配 2：最大长度匹配

    private KeywordFilter()
    {
        try {
            initfiltercode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static KeywordFilter getInstance()
    {
        if (instance == null)
        {
            instance = new KeywordFilter();
        }
        return instance;
    }



    public void addKeywords(List<String> keywords)
    {
        for (int i = 0; i < keywords.size(); i++)
        {
            String key = keywords.get(i).trim();
            HashMap nowhash = null;
            nowhash = keysMap;
            for (int j = 0; j < key.length(); j++)
            {
                char word = key.charAt(j);
                Object wordMap = nowhash.get(word);
                if (wordMap != null)
                {
                    nowhash = (HashMap) wordMap;
                } else
                {
                    HashMap<String, String> newWordHash = new HashMap<String, String>();
                    newWordHash.put("isEnd", "0");
                    nowhash.put(word, newWordHash);
                    nowhash = newWordHash;
                }
                if (j == key.length() - 1)
                {
                    nowhash.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 重置关键词
     */
    public void clearKeywords()
    {
        keysMap.clear();
    }

    /**
     * 检查一个字符串从begin位置起开始是否有keyword符合， 如果有符合的keyword值，返回值为匹配keyword的长度，否则返回零 flag 1:最小长度匹配 2：最大长度匹配
     */
    private int checkKeyWords(String txt, int begin, int flag)
    {
        HashMap nowhash = null;
        nowhash = keysMap;
        int maxMatchRes = 0;
        int res = 0;
        int l = txt.length();
        char word = 0;
        for (int i = begin; i < l; i++)
        {
            word = txt.charAt(i);
            Object wordMap = nowhash.get(word);
            if (wordMap != null)
            {
                res++;
                nowhash = (HashMap) wordMap;
                if (((String) nowhash.get("isEnd")).equals("1"))
                {
                    if (flag == 1)
                    {
                        wordMap = null;
                        nowhash = null;
                        txt = null;
                        return res;
                    } else
                    {
                        maxMatchRes = res;
                    }
                }
            } else
            {
                txt = null;
                nowhash = null;
                return maxMatchRes;
            }
        }
        txt = null;
        nowhash = null;
        return maxMatchRes;
    }

    /**
     * 返回txt中关键字的列表,加样式
     */
    public List<String> getTxtKeyWords(String txt)
    {
        return getTxtKeyWords(txt,true);
    }
    /**
     * 返回txt中关键字的列表,不加样式
     */
    public List<String> getKeyWordsNotStyle(String txt)
    {
        return getTxtKeyWords(txt,false);
    }

    /**
     * 返回txt中关键字的列表
     */
    public List<String> getTxtKeyWords(String txt, boolean isStyle)
    {
        //使用hashset 集合返回 包含那几个 敏感字 不重复显示
        List<String> list = new ArrayList<String>();
        int l = txt.length();
        for (int i = 0; i < l; )
        {
            int len = checkKeyWords(txt, i, matchType);
            if (len > 0)
            {
                String tt = txt.substring(i, i + len) ;
                if(isStyle) {
                    tt="<font color='#ff0000'>"+tt+"</font>";
                }
                list.add(txt.substring(i, i + len) );

                i += len;
            } else
            {
                i++;
            }
        }
        txt = null;
        return list;
    }



    public List<String> getTxtKeyWords(String txt, String styleBegin, String styleEnd)
    {
        //使用hashset 集合返回 包含那几个 敏感字 不重复显示
        List<String> list = new ArrayList<String>();
        int l = txt.length();
        for (int i = 0; i < l; )
        {
            int len = checkKeyWords(txt, i, matchType);
            if (len > 0)
            {
                String tt = styleBegin + txt.substring(i, i + len) + styleEnd;
                list.add(tt);
                i += len;
            } else
            {
                i++;
            }
        }
        txt = null;
        return list;
    }

    /**
     * 仅判断txt中是否有关键字
     */
    public boolean isContentKeyWords(String txt)
    {
        for (int i = 0; i < txt.length(); i++)
        {
            int len = checkKeyWords(txt, i, 1);
            if (len > 0)
            {
                return true;
            }
        }
        txt = null;
        return false;
    }

    /**
     * 初始化敏感词列表
     */
    private void initfiltercode() throws IOException {
        List<String> keywords = new ArrayList<String>();
        InputStreamReader read=null;
        try {
            InputStream in = KeywordFilter.class.getClassLoader().getResourceAsStream("words.properties");
            read = new InputStreamReader(in, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String txt = null;
            while((txt = bufferedReader.readLine()) != null){
                keywords.add(txt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            read.close();
        }


        addKeywords(keywords);
    }

    public int getMatchType()
    {
        return matchType;
    }

    public void setMatchType(int matchType)
    {
        this.matchType = matchType;
    }
}
