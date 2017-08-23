package java.jdk.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;  
  
public class PermSpaceStringConstant {  
  
    public static void main(String[] args) {  
        /* List<String> strs = new ArrayList<String>();
         int i = 0;  
           
         while(true) {  
             strs.add(String.valueOf(i++).intern());   
             System.out.println("We have created " + i + " constant String.");  
         }  */


    }


}
class MultiThread{
    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息         ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息         for (ThreadInfo threadInfo : threadInfos) {
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
// 遍历线程信息，仅打印线程ID和线程名称信息
for(ThreadInfo threadInfo : threadInfos) { System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
                getThreadName());
    }
}
}