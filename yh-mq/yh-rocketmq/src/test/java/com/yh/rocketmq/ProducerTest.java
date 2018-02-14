package com.yh.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ProducerTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            // 声明并初始化一个producer
            // 需要一个producer group名字作为构造方法的参数，这里为ordered_producer
            DefaultMQProducer orderedProducer = new DefaultMQProducer("ordered_producer");

            // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
            //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
            orderedProducer.setNamesrvAddr("192.168.156.101:9876");

            // 调用start()方法启动一个producer实例
            orderedProducer.start();

            // 自定义一个tag数组
            String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

            // 发送10条消息到Topic为TopicTestOrdered，tag为tags数组按顺序取值，
            // key值为“KEY”拼接上i的值，消息内容为“Hello RocketMQ”拼接上i的值
            for (int i = 0; i < 10; i++) {

                int orderId = i % 10;
                Message msg =
                        new Message("TopicTestOrdered", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                /*延时消息

                延时消息，简单来说就是当 producer 将消息发送到 broker 后，会延时一定时间后才投递给 consumer 进行消费。

                RcoketMQ的延时等级为：1s，5s，10s，30s，1m，2m，3m，4m，5m，6m，7m，8m，9m，10m，20m，30m，1h，2h。level=0，表示不延时。level=1，表示 1 级延时，对应延时 1s。level=2 表示 2 级延时，对应5s，以此类推。

                这种消息一般适用于消息生产和消费之间有时间窗口要求的场景。比如说我们网购时，下单之后是有一个支付时间，超过这个时间未支付，系统就应该自动关闭该笔订单。那么在订单创建的时候就会就需要发送一条延时消息（延时15分钟）后投递给 consumer，consumer 接收消息后再对订单的支付状态进行判断是否关闭订单。

                作者：冯先生的笔记
                链接：https://www.jianshu.com/p/11e875074a8f
                來源：简书
                著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
                // 这里设置需要延时的等级即可
                msg.setDelayTimeLevel(3);
                SendResult sendResult = orderedProducer.send(msg, new MessageQueueSelector() {

                    // 选择发送消息的队列
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                        // arg的值其实就是orderId
                        Integer id = (Integer) arg;

                        // mqs是队列集合，也就是topic所对应的所有队列
                        int index = id % mqs.size();

                        // 这里根据前面的id对队列集合大小求余来返回所对应的队列
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.println(sendResult);
            }

            orderedProducer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
