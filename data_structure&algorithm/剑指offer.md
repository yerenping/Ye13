## 一、数据结构

### 1、链表

#### JZ6 从尾到头打印链表

![image-20211121112445356](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211121112445.png)



> 解法一 直接遍历

java实现

```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listNode; // 中间变量
        while(tmp != null){
            list.add(0,tmp.val);
            tmp = tmp.next;
        }
        return list;
    }
}
```

c语言实现

```c
/**
 * struct ListNode {
 *	int val;
 *	struct ListNode *next;
 * };
 */
/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param listNode ListNode类 
 * @return int整型一维数组
 * @return int* returnSize 返回数组行数
 */
int* printListFromTailToHead(struct ListNode* listNode, int* returnSize ) {
    struct ListNode* p = listNode;
    int i=0;
    int* arr=NULL;
    while(p != NULL){
        p=p->next;
        i++;
    }
    *returnSize = i;
    arr = (int*)malloc(sizeof(int)*i);
    p=listNode;
    while(p != NULL){
        arr[--i]=p->val;
        p=p->next;
    }
    return arr;
}
```





#### JZ24 反转链表

![image-20211121133520483](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211121133520.png) 



C语言实现 

思路：https://www.bilibili.com/video/BV1KZ4y157Up

```c
/**
 * struct ListNode {
 *	int val;
 *	struct ListNode *next;
 * };
 *
 * C语言声明定义全局变量请加上static，防止重复定义
 */

/**
 * 
 * @param pHead ListNode类 
 * @return ListNode类
 */
/*


*/
struct ListNode* ReverseList(struct ListNode* pHead ) {
    struct ListNode* pre = NULL;
    struct ListNode* cur = pHead;
    struct ListNode* tmp = NULL;
    while(cur != NULL){
        
        tmp = cur->next;
        cur->next = pre;
        pre = cur;
        cur = tmp;
    }
    return pre;
}
```





#### JZ52 两个链表的第一个公共结点

![image-20211122082138806](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211122082138.png)



很巧妙的方法-题解：https://www.bilibili.com/video/BV1iy4y1J7VW

![36](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211122082436.gif) 

C语言实现-代码

```c
/**
 * struct ListNode {
 *	int val;
 *	struct ListNode *next;
 * };
 *
 * C语言声明定义全局变量请加上static，防止重复定义
 */

/**
 * 
 * @param pHead1 ListNode类 
 * @param pHead2 ListNode类 
 * @return ListNode类
 */
struct ListNode* FindFirstCommonNode(struct ListNode* pHead1, struct ListNode* pHead2 ) {
    struct ListNode* p1 = pHead1;
    struct ListNode* p2 = pHead2;
    while(p1 != p2){
        p1 = p1?p1->next:pHead2;
        p2 = p2?p2->next:pHead1;
    }
    return p1;
    
}
```





### 2、树



#### JZ55 二叉树的深度



![image-20211121153340649](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211121153340.png)



C语言 递归实现

```c
/**
 * struct TreeNode {
 *	int val;
 *	struct TreeNode *left;
 *	struct TreeNode *right;
 * };
 *
 * C语言声明定义全局变量请加上static，防止重复定义
 */
/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param pRoot TreeNode类 
 * @return int整型
 */
int TreeDepth(struct TreeNode* pRoot ) {
    int m,n;
    if(pRoot == NULL){
        return 0;
    }else{
        m = TreeDepth(pRoot->left);
        n = TreeDepth(pRoot->right);
        if(m>n){
            return(m+1);
        }else{
            return(n+1);
        }
    }
}
```

### 3、队列与栈

#### JZ30 包含min函数的栈

![image-20211121151041651](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211121151041.png)



C语言：基于数组实现顺序栈

```c

/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param value int整型 
 * @return 无
 *
 * C语言声明定义全局变量请加上static，防止重复定义
 */
static int str[300];
int num;
void push(int value ) {
    // num所指向的位置是待插入位
    str[num++] = value;
}

/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param 无 
 * @return 无
 */
void pop() {
   if(num!=0){
        str[--num] = 0; // 所谓出栈
   }
}

/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param 无 
 * @return int整型
 */
int top() {
    // 如果栈空，则返回0
    if(num == 0){
        return 0;
    }
    return str[num-1];
}

/**
 * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
 *
 * 
 * @param 无 
 * @return int整型
 */
int min() {
    int minNum = str[0];
    for(int i=1; i<num; i++){
        if(minNum > str[i]){
            minNum = str[i];
        }
    }
    return minNum;
}
```



#### JZ25 合并两个排序的链表

![image-20211121202623207](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211121202623.png)

```c
/**
 * struct ListNode {
 *	int val;
 *	struct ListNode *next;
 * };
 *
 * C语言声明定义全局变量请加上static，防止重复定义
 */

/**
 * 
 * @param pHead1 ListNode类 
 * @param pHead2 ListNode类 
 * @return ListNode类
 */
struct ListNode* Merge(struct ListNode* pHead1, struct ListNode* pHead2 ) {

   struct ListNode* p1 = pHead1;
   struct ListNode* p2 = pHead2;
   struct ListNode* p =(struct ListNode*)malloc(sizeof(struct ListNode));// p为头结点
   struct ListNode* pHead = p; // 带头结点的链表
   while(p1 && p2){
       struct ListNode* node =(struct ListNode*)malloc(sizeof(struct ListNode));
       if(p1->val<=p2->val){ // 如果p1小于等于p2，则将其接入到新的链表中，p1后移
           node->val = p1->val;
           p1 = p1->next;
       }else{
           node->val = p2->val;
           p2 = p2->next;
       }
       p->next = node;
       p = p->next;
   }
   p->next = p1==NULL?p2:p1;
   return pHead->next;
}
```





## 二、算法



### 1、搜索算法



### 2、动态规划





### 3、回溯



### 4、排序



#### 5、位运算





### 6、模拟



#### 7、其他算法



