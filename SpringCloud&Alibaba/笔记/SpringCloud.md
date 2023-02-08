# 一、Spring Cloud Netflix

参考：https://www.kuangstudy.com/bbs/1374942542566551554

## （0）面试题

### 1、什么是微服务

1.1 就目前而言，对于微服务，业界并没有一个统一的，标准的定义。

1.2 但通常而言，微服务架构是一种架构模式，或者说是一种架构风格，**它体长将单一的应用程序划分成一组小的服务**，每个服务运行在其独立的自己的进程内，服务之间互相协调，互相配置，为用户提供最终价值，服务之间采用轻量级的通信机制(**HTTP**)互相沟通，每个服务都围绕着具体的业务进行构建，并且能狗被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应该根据业务上下文，选择合适的语言，工具(**Maven**)对其进行构建，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储。

1.3 技术维度角度出发：

微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进程的概念，能够自行单独启动或销毁，拥有自己独立的数据库。



### 2、微服务与微服务架构

> 微服务

强调的是服务的大小，它关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭义的看，可以看作是IDEA中的一个个微服务工程，或者Moudel。IDEA 工具里面使用Maven开发的一个个独立的小Moudel，它具体是使用SpringBoot开发的一个小模块，专业的事情交给专业的模块来做，一个模块就做着一件事情。强调的是一个个的个体，每个个体完成一个具体的任务或者功能。



> 微服务架构

一种新的架构形式，Martin Fowler 于2014年提出。

微服务架构是一种架构模式，它体长将单一应用程序划分成一组小的服务，服务之间相互协调，互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务与服务之间采用轻量级的通信机制**(如HTTP)**互相协作，每个服务都围绕着具体的业务进行构建，并且能够被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言、工具**(如Maven)**对其进行构建。



### 3、微服务优缺点

> 优点

- 单一职责原则；
- 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求；
- 开发简单，开发效率高，一个服务可能就是专一的只干一件事；
- 微服务能够被小团队单独开发，这个团队只需2-5个开发人员组成；
- 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的；
- 微服务能使用不同的语言开发；
- 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins，Hudson，bamboo；
- 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果，无需通过合作才能体现价值；
- 微服务允许利用和融合最新技术；
- **微服务只是业务逻辑的代码，不会和HTML，CSS，或其他的界面混合;**
- **每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一的数据库；**

> 缺点

- 开发人员要处理分布式系统的复杂性；
- 多服务运维难度，随着服务的增加，运维的压力也在增大；
- 系统部署依赖问题；
- 服务间通信成本问题；
- 数据一致性问题；
- 系统集成测试问题；
- 性能和监控问题；



### 4、微服务技术栈有那些？

| **微服务技术条目**                     | 落地技术                                                     |
| -------------------------------------- | ------------------------------------------------------------ |
| 服务开发                               | SpringBoot、Spring、SpringMVC等                              |
| 服务配置与管理                         | Netfix公司的Archaius、阿里的Diamond等                        |
| 服务注册与发现                         | Eureka、Consul、Zookeeper、Nacos等                           |
| 服务调用                               | Rest、PRC、gRPC                                              |
| 服务熔断器                             | Hystrix、Envoy等                                             |
| 负载均衡                               | Ribbon、Nginx等                                              |
| 服务接口调用(客户端调用服务的简化工具) | Fegin等                                                      |
| 消息队列                               | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                       | SpringCloudConfig、Chef等                                    |
| 服务路由(API网关)                      | Zuul、Getway                                                 |
| 服务监控                               | Zabbix、Nagios、Metrics、Specatator等                        |
| 全链路追踪                             | Zipkin、Brave、Dapper等                                      |
| 数据流操作开发包                       | SpringCloud Stream(封装与Redis，Rabbit，Kafka等发送接收消息) |
| 时间消息总栈                           | SpringCloud Bus                                              |
| 服务部署                               | Docker、OpenStack、Kubernetes等                              |



### 5、为什么选择SpringCloud作为微服务架构

1. 选型依据

   - 整体解决方案和框架成熟度
   - 社区热度
   - 可维护性
   - 学习曲线

2. 当前各大IT公司用的微服务架构有那些？

   - 阿里：dubbo+HFS

   - 京东：JFS

   - 新浪：Motan

   - 当当网：DubboX

     …

### 6、 SpringCloud和SpringBoot的关系

- SpringBoot专注于开苏方便的开发单个个体微服务；
- SpringCloud是关注**全局的微服务协调整理治理**框架，它将SpringBoot开发的一个个单体微服务，整合并管理起来，为各个微服务之间提供：配置管理、服务发现、断路器、路由、为代理、事件总栈、全局锁、决策竞选、分布式会话等等集成服务；
- SpringBoot可以离开SpringCloud独立使用，开发项目，但SpringCloud离不开SpringBoot，属于依赖关系；
- SpringBoot专注于快速、方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架；



### 7、 Dubbo 和 SpringCloud技术选型

##### 1. 分布式+服务治理Dubbo

目前成熟的互联网架构，应用服务化拆分 + 消息中间件

##### 2. Dubbo 和 SpringCloud对比

可以看一下社区活跃度：

https://github.com/dubbo

https://github.com/spring-cloud

**对比结果：**

|              | Dubbo         | SpringCloud                  |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | Zookeeper     | Spring Cloud Netfilx Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netfilx Hystrix |
| 服务网关     | 无            | Spring Cloud Netfilx Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总栈     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

**最大区别：Spring Cloud 抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式**

严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这个优点在当下强调快速演化的微服务环境下，显得更加合适。

**品牌机和组装机的区别**

**社区支持与更新力度的区别**

**总结：**二者解决的问题域不一样：Dubbo的定位是一款RPC框架，而SpringCloud的目标是微服务架构下的一站式解决方案。





### 8、SpringCloud能干嘛？

- Distributed/versioned configuration 分布式/版本控制配置
- Service registration and discovery 服务注册与发现
- Routing 路由
- Service-to-service calls 服务到服务的调用
- Load balancing 负载均衡配置
- Circuit Breakers 熔断器
- Distributed messaging 分布式消息管理
- …













![image-20230111115724037](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230111115724.png)



## （1）Eureka 注册中心

- Netflix在涉及Eureka时，遵循的就是API原则.
- Eureka是Netflix的有个子模块，也是核心模块之一。Eureka是基于REST的服务，用于定位服务，以实现云端中间件层服务发现和故障转移，服务注册与发现对于微服务来说是非常重要的，有了服务注册与发现，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，功能类似于Dubbo的注册中心，比如Zookeeper.

> 三大角色

- Eureka Server：提供服务的注册与发现
- Service Provider：服务生产方，将自身服务注册到Eureka中，从而使服务消费方能狗找到
- Service Consumer：服务消费方，从Eureka中获取注册服务列表，从而找到消费服务



> EureKa自我保护机制：好死不如赖活着

一句话总结就是：**某时刻某一个微服务不可用，eureka不会立即清理，依旧会对该微服务的信息进行保存！**

- 默认情况下，当eureka server在一定时间内没有收到实例的心跳，便会把该实例从注册表中删除（**默认是90秒**），但是，如果短时间内丢失大量的实例心跳，便会触发eureka server的自我保护机制，比如在开发测试时，需要频繁地重启微服务实例，但是我们很少会把eureka server一起重启（因为在开发过程中不会修改eureka注册中心），**当一分钟内收到的心跳数大量减少时，会触发该保护机制**。可以在eureka管理界面看到Renews threshold和Renews(last min)，当后者（最后一分钟收到的心跳数）小于前者（心跳阈值）的时候，触发保护机制，会出现红色的警告：`EMERGENCY!EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT.RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEGING EXPIRED JUST TO BE SAFE.`从警告中可以看到，eureka认为虽然收不到实例的心跳，但它认为实例还是健康的，eureka会保护这些实例，不会把它们从注册表中删掉。
- 该保护机制的目的是避免网络连接故障，在发生网络故障时，微服务和注册中心之间无法正常通信，但服务本身是健康的，不应该注销该服务，如果eureka因网络故障而把微服务误删了，那即使网络恢复了，该微服务也不会重新注册到eureka server了，因为只有在微服务启动的时候才会发起注册请求，后面只会发送心跳和服务列表请求，这样的话，该实例虽然是运行着，但永远不会被其它服务所感知。所以，eureka server在短时间内丢失过多的客户端心跳时，会进入自我保护模式，该模式下，eureka会保护注册表中的信息，不在注销任何微服务，当网络故障恢复后，eureka会自动退出保护模式。自我保护模式可以让集群更加健壮。
- 但是我们在开发测试阶段，需要频繁地重启发布，如果触发了保护机制，则旧的服务实例没有被删除，这时请求有可能跑到旧的实例中，而该实例已经关闭了，这就导致请求错误，影响开发测试。所以，在开发测试阶段，我们可以把自我保护模式关闭，只需在eureka server配置文件中加上如下配置即可：`eureka.server.enable-self-preservation=false`【不推荐关闭自我保护机制】

> 对比和Zookeeper区别

###### **1. 回顾CAP原则**

RDBMS (MySQL\Oracle\sqlServer) ===> ACID

NoSQL (Redis\MongoDB) ===> CAP

###### **2. ACID是什么？**

- A (Atomicity) 原子性
- C (Consistency) 一致性
- I (Isolation) 隔离性
- D (Durability) 持久性

###### **3. CAP是什么?**

- C (Consistency) 强一致性
- A (Availability) 可用性
- P (Partition tolerance) 分区容错性

CAP的三进二：CA、AP、CP

###### **4. CAP理论的核心**

- 一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求
- 根据CAP原理，将NoSQL数据库分成了满足CA原则，满足CP原则和满足AP原则三大类
  - CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差
  - CP：满足一致性，分区容错的系统，通常性能不是特别高
  - AP：满足可用性，分区容错的系统，通常可能对一致性要求低一些

###### **5. 作为分布式服务注册中心，Eureka比Zookeeper好在哪里？**

著名的CAP理论指出，一个分布式系统不可能同时满足C (一致性) 、A (可用性) 、P (容错性)，由于分区容错性P再分布式系统中是必须要保证的，因此我们只能再A和C之间进行权衡。

- Zookeeper 保证的是 CP —> 满足**一致性，分区容错**的系统，通常性能不是特别高
- Eureka 保证的是 AP —> 满足**可用性，分区容错**的系统，通常可能对一致性要求低一些

**Zookeeper保证的是CP**

当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。也就是说，**服务注册功能对可用性的要求要高于一致性**。但zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30-120s，且选举期间整个zookeeper集群是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率发生的事件，虽然服务最终能够恢复，但是，漫长的选举时间导致注册长期不可用，是不可容忍的。

**Eureka保证的是AP**

Eureka看明白了这一点，因此在设计时就优先保证可用性。**Eureka各个节点都是平等的**，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有之中自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

- Eureka不在从注册列表中移除因为长时间没收到心跳而应该过期的服务
- Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上 (即保证当前节点依然可用)
- 当网络稳定时，当前实例新的注册信息会被同步到其他节点中

因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪



## （2）Ribbon

> 同SpringCloudAlibaba中的Ribbon

## （3）Feign 负载均衡

> Feign是声明式Web Service客户端，它让微服务之间的调用变得更简单，类似controller调用service。SpringCloud集成了Ribbon和Eureka，可以使用Feigin提供负载均衡的http客户端

Feign，主要是社区版，大家都习惯面向接口编程。这个是很多开发人员的规范。调用微服务访问两种方法

1. 微服务名字 【ribbon】
2. 接口和注解 【feign】



**Feign能干什么？**

- Feign旨在使编写Java Http客户端变得更容易
- 前面在使用**Ribbon** + **RestTemplate**时，利用**RestTemplate**对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一个客户端类来包装这些依赖服务的调用。所以，**Feign**在此基础上做了进一步的封装，由他来帮助我们定义和实现依赖服务接口的定义，在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它 (类似以前Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解)，即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon 时，自动封装服务调用客户端的开发量。

**Feign默认集成了Ribbon**

- 利用**Ribbon**维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与**Ribbon**不同的是，通过**Feign**只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。





## （4）Hystrix 服务熔断



> 分布式系统面临的问题

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免失败！



#### 服务雪崩

多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的“扇出”，如果扇出的链路上**某个微服务的调用响应时间过长，或者不可用**，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。



![在这里插入图片描述](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120101123.png)

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几十秒内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障，**这些都表示需要对故障和延迟进行隔离和管理，以达到单个依赖关系的失败而不影响整个应用程序或系统运行**。

我们需要，**弃车保帅**！



#### 什么是Hystrix？

**Hystrix**是一个应用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时，异常等，**Hystrix** 能够保证在一个依赖出问题的情况下，不会导致整个体系服务失败，避免级联故障，以提高分布式系统的弹性。

“**断路器**”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控 (类似熔断保险丝) ，**向调用方返回一个服务预期的，可处理的备选响应 (FallBack) ，而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间，不必要的占用**，从而避免了故障在分布式系统中的蔓延，乃至雪崩。



![在这里插入图片描述](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120101314.png)

####  Hystrix能干嘛？

- 服务降级
- 服务熔断
- 服务限流
- 接近实时的监控
- …

当一切正常时，请求流可以如下所示：

![format_png 2](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9naXRodWIuY29tL05ldGZsaXgvSHlzdHJpeC93aWtpL2ltYWdlcy9zb2EtMS02NDAucG5n?x-oss-process=image/format,png)

当许多后端系统中有一个潜在阻塞服务时，它可以阻止整个用户请求：

![format_png 3](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9naXRodWIuY29tL05ldGZsaXgvSHlzdHJpeC93aWtpL2ltYWdlcy9zb2EtMi02NDAucG5n?x-oss-process=image/format,png)

随着大容量通信量的增加，单个后端依赖项的潜在性会导致所有服务器上的所有资源在几秒钟内饱和。

应用程序中通过网络或客户端库可能导致网络请求的每个点都是潜在故障的来源。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，从而备份队列、线程和其他系统资源，从而导致更多跨系统的级联故障。

![format_png 4](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9naXRodWIuY29tL05ldGZsaXgvSHlzdHJpeC93aWtpL2ltYWdlcy9zb2EtMy02NDAucG5n?x-oss-process=image/format,png)

当使用**Hystrix**包装每个基础依赖项时，上面的图表中所示的体系结构会发生类似于以下关系图的变化。**每个依赖项是相互隔离的**，限制在延迟发生时它可以填充的资源中，并包含在回退逻辑中，该逻辑决定在依赖项中发生任何类型的故障时要做出什么样的响应：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200521131820586.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzU5MTk4MA==,size_16,color_FFFFFF,t_70#pic_center)

**官网资料**：https://github.com/Netflix/Hystrix/wiki

#### 服务熔断

##### 什么是服务熔断?

**熔断机制是赌赢雪崩效应的一种微服务链路保护机制**。

当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回错误的响应信息**。检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阀值缺省是**5秒内20次调用失败，就会启动熔断机制**。熔断机制的注解是：`@HystrixCommand`。

服务熔断解决如下问题：

- 当所依赖的对象不稳定时，能够起到快速失败的目的；
- 快速失败后，能够根据一定的算法动态试探所依赖对象是否恢复。



## （5）Zull路由网关

> 什么是zuul?

Zull包含了对请求的**路由**(用来跳转的)和**过滤**两个最主要功能：

其中**路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础**，而过**滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础**。Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。

![在这里插入图片描述](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120114323.png)

**注意**：Zuul 服务最终还是会注册进 Eureka

**提供**：代理 + 路由 + 过滤 三大功能！

> Zuul 能干嘛？

- 路由
- 过滤















# 二、SpringCloudAlibaba

- 需要的依赖（SpringBoot + SpringCloud + SpringCloudAlibaba）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
   <!-- springboot-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.yrp</groupId>
    <artifactId>springcloud_alibaba_demo01</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springcloud_alibaba_demo01</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <dependencyManagement>
        <dependencies>
            <!--springcloud-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR3</version>
            </dependency>
            <!-- springcloudalibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```









## （1）Nacos服务注册与发现

**1、下载地址：https://github.com/alibaba/nacos/releases（mac下载tar包）**

**2、数据库创建**
<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230101213430.png" alt="image-20230101213406390" style="zoom:50%;" /> 

**2、启动和关闭命令** 

```shell
# standalone代表着单机模式运行，非集群模式
sh startup.sh -m standalone
# 停止
sh shutdown.sh

# 查看进程号
lsof -i:8848
# 杀进程
kill -9 7532
```



**3、访问地址：http://127.0.0.1:8848/nacos**



## （2）Ribbon



- **Ribbon是什么？**

> Spring Cloud Ribbon 是基于Netflix Ribbon 实现的一套**客户端负载均衡**的工具。
> 简单的说，Ribbon 是 Netflix 发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将 Netflix 的中间层服务连接在一起。Ribbon 的客户端组件提供一系列完整的配置项，如：连接超时、重试等。简单的说，就是在配置文件中列出 LoadBalancer (简称LB：负载均衡) 后面所有的及其，Ribbon 会自动的帮助你基于某种规则 (如简单**轮询**，**随机连接**等等) 去连接这些机器。我们也容易使用 Ribbon 实现自定义的负载均衡算法！



- **Ribbon能干嘛？**

- LB，即负载均衡 (LoadBalancer) ，在微服务或分布式集群中经常用的一种应用。
- 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA (高用)。
- 常见的负载均衡软件有 Nginx、`Lvs` 等等。
- Dubbo、SpringCloud 中均给我们提供了负载均衡，**SpringCloud 的负载均衡算法可以自定义**。
- 负载均衡简单分类：
  - 集中式LB
    - 即在服务的提供方和消费方之间使用独立的LB设施，如**Nginx(反向代理服务器)**，由该设施负责把访问请求通过某种策略转发至服务的提供方！
  - 进程式 LB
    - 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    - **Ribbon 就属于进程内LB**，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址！



>  Ribbon不是Spring Cloud Alibaba的组件，而是Netflix的组提供的，默认采用`轮询算法，依次调用`

- **Ribbon使用方式**

在RestTemplate上添加`@LoadBalanced`注解

![image-20230102225629418](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230102225629.png)



- **Robbon采用的算法**

![image-20230119171651465](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230119171651.png)



**1、轮询（默认）**

> 交替选择执行

**2、随机方式**

​	2.1 配置（在consumer的application.yml中进行配置）

```yaml
server:
  port: 9999
provider:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```

**3、基于权重的算法**

​	2.1 配置

​	2.1.1 添加配置文件，在config包下添加

```java
@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //读取配置文件
    }

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) loadBalancer;
        //获取要请求的微服务名称
        String name = baseLoadBalancer.getName();
        //获取服务发现的相关API
        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
        try {
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("选择的实例是port={},instance={}",instance.getPort(),instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

2.1.2 设置application.yml为随机方式

```yaml
provider:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```

2.1.3 修改权重值

![image-20230102231126585](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230102231126.png)



## （3）Sentinel 服务限流降级

### 1、背景知识-雪崩效应

> 当服务集群中某个服务崩溃了，导致其他服务只能等待，造成整个系统崩溃

- ==**解决方案**==

  **（1）设置线程超时**：当某个请求在特定的时间内无法成功调用，我们就放弃掉这个线程

  **（2）设置限流**：对集群中某个服务设置限流，当该服务的流量达到上限由，就无法再被请求访问

  **（3）熔断器：**Sentinel、Hystrix

![image-20230103162045200](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230103162045.png)



- 降级（砍功能-应该自身-客户端）

> 限制一些功能的使用，系统将一些不常用的功能给停用

- 限流

> 只接收系统能够承载的访问量：比如说我设定了只能处理10个情况，你来20个，另外10个我就不处理了

- 熔断（砍功能-服务端）

> 应该外部系统的故障，我要调用别人，别人出问题了，我就直接抛出个异常

### 2、配置

（1）引入依赖，在producer中添加sentinel和spring actuator

> 用actuator对所有的请求

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



（2）修改配置文件application.yml

> 将所有的请求服务都开放出来

```yml
# 当前producer所有的请求，我都会进行监控
management:
  endpoints:
    web:
      exposure:
        include: '*'

```

 （3）下载 Sentinel 控制台，解压，启动。

- 启动Sentinel 客户端

  - 启动命令

  ```shell
  java -jar sentinel-dashboard-1.7.2.jar start
  
  java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar
  
  ```

  - 默认用户名和密码都为`sentinel`

- 启动nacos

  ```shell
  sh startup.sh -m standalone
  ```

- 启动producer项目

- 添加依赖 pom.xml

```java
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
    <version>1.7.1</version>
</dependency>

<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-web-servlet</artifactId>
    <version>1.7.1</version>
</dependency>
```

- 编写配置类

```java
package com.southwind.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY,"false");
        registrationBean.setName("sentinelFilter");
        return registrationBean;
    }
}
```





### 3、流量控制

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230107205232.png" alt="image-20230107205232131" style="zoom:50%;" /> 

- Qps  + 单击阈值 1：每秒钟内只能发起一次请求，如果超过一次，则直接抛出异常

- 快速失败：直接抛出异常

- Warm up 预热

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230107205550.png" alt="image-20230107205550183" style="zoom:50%;" /> 

- 排队等待：字面上的意思

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230107205809.png" alt="image-20230107205808905" style="zoom:50%;" /> 



### 4、降级

> RT

单个请求的响应时间超过阈值，则进入准降级状态，==**接下来 1 S 内连续 5 个请求响应时间均超过阈值**==，就进行降级，持续时间为时间窗口的值。

> 异常比例

每秒异常数量占通过量的比例大于阈值，就进行降级处理，持续时间为时间窗口的值。

> 异常数

1 分钟内的异常数超过阈值就进行降级处理，时间窗口的值要大于 60S，否则刚结束熔断又进入下一次熔断了。







### 5、 热点规则



热点规则是流控规则的更细粒度操作，可以具体到对某个热点参数的限流，设置限流之后，如果带着限流参数的请求量超过阈值，则进行限流，时间为统计窗口时长。

必须要添加 @SentinelResource，即对资源进行流控。

```java
@GetMapping("/hot")
@SentinelResource("hot")
public String hot(
        @RequestParam(value = "num1",required = false) Integer num1,
        @RequestParam(value = "num2",required = false) Integer num2){
    return num1+"-"+num2;
}
```



## （4）RocketMQ

#### 1、安装 RocketMQ

1、传入 Linux 服务器

2、解压缩

```
unzip rocketmq-all-4.7.1-bin-release.zip
```

3、启动 NameServer

```
nohup ./bin/mqnamesrv &
```

4、检查是否启动成功

```
netstat -an | grep 9876
```

![image-20230108090008329](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230108090008.png)

5、启动 Broker

启动之前需要编辑配置文件，修改 JVM 内存设置，默认给的内存 4 GB，超过我们的 JVM 了。

```shell
cd bin
# 修改其内容 -内存改小
vim runserver.sh
vim runbroker.sh
```

我们在云服务器上的话，还需要修改ip

```shell
vim broker.conf
```

![image-20230108084316621](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230108085648.png)

```shell
namesrvAddr = 47.143.64.231:9876
brokerIP1=47.143.64.231
```





启动 Broker

> 本机的方式

```
nohup ./mqbroker -n localhost:9876 &
```

> 云服务器

```shell
nohup sh bin/mqbroker -n 43.143.64.231:9876 -c conf/broker.conf  >  bin/startMqBroker.log 2>&1 &
# 查看是否启动成功
tail -f ~/logs/rocketmqlogs/broker.log
```





关闭 RocketMQ

```
cd bin
./mqshutdown broker
./mqshutdown namesrv
```





#### 2、Spring Boot 整合 RocketMQ

> provider

1、pom.xml

```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client</artifactId>
    <version>4.7.0</version>
</dependency>
```

2、application.yml

```yaml
rocketmq:
  name-server: 192.168.248.129:9876
  producer:
    group: myprovider
```

3、Order

```java
package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String buyerName;
    private String buyerTel;
    private String address;
    private Date createDate;
}
```

4、Controller

```java
@Autowired
private RocketMQTemplate rocketMQTemplate;

@GetMapping("/create")
public Order create(){
    Order order = new Order(
        1,
        "张三",
        "123123",
        "软件园",
        new Date()
    );
    this.rocketMQTemplate.convertAndSend("myTopic",order);
    return order;
}
```

> consumer

1、pom.xml

```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client</artifactId>
    <version>4.7.0</version>
</dependency>
```

2、application.yml

```yaml
rocketmq:
  name-server: 192.168.248.129:9876
```

3、Service

```java
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "myConsumer",topic = "myTopic")
public class SmsService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("新订单{},发短信",order);
    }
}
```



## （5）Gateway-路由网关

==Spring Cloud Gateway 是基于 Netty，跟 Servlet 不兼容，所以你的工程中不能出现 Servlet 的组件 。==

==也就是说不能有web（spring-mvc）相关的jar==

0、移除掉父工程的web依赖

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```



1、pom.xml

注意，一定不能出现 spring web 的依赖，因为 Gateway 与 Servlet 不兼容。

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
            <version>2.2.5.RELEASE</version>
        </dependency>
```

2、application.yml

```yaml
server:
  port: 8010
spring:
  application:
    name: gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      routes: 
        - id: provider_route   
          uri: http://localhost:8081 
          predicates: 
            - Path=/provider/** 
          filters:
            - StripPrefix=1
```

上面这种做法其实没有用到 nacos ，现在我们让 gateway 直接去 nacos 中发现服务，配置更加简单了。

1、pom.xml 引入 nacos

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

2、application.yml

```yaml
server:
  port: 8010
spring:
  application:
    name: gateway
  cloud:
      gateway:
        discovery:
          locator:
            enabled: true
```



### Gateway 限流

### （1）基于路由限流

1、pom.xml

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-spring-cloud-gateway-adapter</artifactId>
</dependency>
```

2、配置类

```java
package com.southwind.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;


    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    //配置限流的异常处理
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    //配置初始化的限流参数
    @PostConstruct
    public void initGatewayRules(){
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(
                new GatewayFlowRule("provider_route")
                .setCount(1)
                .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

    //初始化限流过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    //自定义限流异常页面
    @PostConstruct
    public void initBlockHandlers(){
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map map = new HashMap();
                map.put("code",0);
                map.put("msg","被限流了");
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
```

3、application.yml

```yaml
server:
  port: 8010
spring:
  application:
    name: gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
        - id: provider_route
          uri: http://localhost:8081
          predicates:
            - Path=/provider/**
          filters:
            - StripPrefix=1
```

### （2）基于 API 分组限流

1、修改配置类，添加基于 API 分组限流的方法，修改初始化的限流参数

```java
package com.southwind.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;


    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    //配置限流的异常处理
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    //配置初始化的限流参数
    @PostConstruct
    public void initGatewayRules(){
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("provider_api1").setCount(1).setIntervalSec(1));
        rules.add(new GatewayFlowRule("provider_api2").setCount(1).setIntervalSec(1));
        GatewayRuleManager.loadRules(rules);
    }

    //初始化限流过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    //自定义限流异常页面
    @PostConstruct
    public void initBlockHandlers(){
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map map = new HashMap();
                map.put("code",0);
                map.put("msg","被限流了");
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    //自定义API分组
    @PostConstruct
    private void initCustomizedApis(){
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("provider_api1")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    add(new ApiPathPredicateItem().setPattern("/provider/api1/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        ApiDefinition api2 = new ApiDefinition("provider_api2")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    add(new ApiPathPredicateItem().setPattern("/provider/api2/demo1"));
                }});
        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
```

2、Controller 添加方法

```java
@GetMapping("/api1/demo1")
public String demo1(){
    return "demo";
}

@GetMapping("/api1/demo2")
public String demo2(){
    return "demo";
}

@GetMapping("/api2/demo1")
public String demo3(){
    return "demo";
}

@GetMapping("/api2/demo2")
public String demo4(){
    return "demo";
}
```

也可以基于 Nacos 服务发现组件进行限流

```yaml
server:
  port: 8010
spring:
  application:
    name: gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
```

API 分组代码修改，改为 discovery 中的服务名。

```java
ApiDefinition api2 = new ApiDefinition("provider_api2")
        .setPredicateItems(new HashSet<ApiPredicateItem>(){{
            add(new ApiPathPredicateItem().setPattern("/p1/api2/demo1"));
        }});
```



## （6）分布式事务

#### 模拟分布式事务异常

1、创建两个工程 order、pay，pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

2、建两个数据库 order、pay，两个微服务分别访问。

3、分别写两个服务的 application.yml

```yaml
server:
  port: 8010
spring:
  application:
    name: order
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/order
```

```yaml
server:
  port: 8020
spring:
  application:
    name: pay
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/pay
```

4、分别写两个 Service

```java
package com.southwind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(){
        this.jdbcTemplate.update("insert into orders(username) values ('张三')");
    }
}
```

```java
package com.southwind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PayService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(){
        this.jdbcTemplate.update("insert into pay(username) values ('张三')");
    }
}
```

5、控制器 Order 通过 RestTemplate 调用 Pay 的服务

```java
package com.southwind.controller;

import com.southwind.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/save")
    public String save(){
        //订单
        this.orderService.save();
        int i = 10/0;
        //支付
        this.restTemplate.getForObject("http://localhost:8020/save",String.class);
        return "success";
    }
}
```

```java
package com.southwind.controller;

import com.southwind.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping("/save")
    public String save(){
        this.payService.save();
        return "success";
    }
}
```

6、启动类

```java
package com.southwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

```java
package com.southwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
```

分布式异常模拟结束，Order 存储完成之后，出现异常，会导致 Pay 无法存储，但是 Order 数据库不会进行回滚。



#### Seata 解决

1、下载

2、解压，修改两个文件

regisry.conf

```conf
registry {
  type = "nacos"
  nacos {
    serverAddr = "localhost"
    namespace = "public"
    cluster = "default"
  }
}

config {
  type = "nacos"
  nacos {
    serverAddr = "localhost"
    namespace = "public"
    cluster = "default"
  }
}
```

nacos-config.txt

![image-20200624170027580](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624170027580.png)

3、启动 Nacos，运行 nacos-config.sh 将 Seata 配置导入 Nacos

进入 conf，右键 Git Bash Here

```
cd conf
sh nacos-config.sh 127.0.0.1
```

执行成功，刷新 Nacos，配置加入

![image-20200624170411851](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624170411851.png)

nacos-config.txt 配置已生效

![image-20200624170446667](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624170446667.png)

4、启动 Seata Server，  **JDK 8 以上环境无法启动**

```
cd bin
seata-server.bat -p 8090 -m file
```

![image-20200624170701118](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624170701118.png)

启动成功，Nacos 注册成功。

![image-20200624171016755](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624171016755.png)

Seata 服务环境搭建完毕，接下来去应用中添加。

1、初始化数据库，在两个数据库中添加事务日志记录表，SQL Seata 已经提供。

![image-20200624171211591](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624171211591.png)

2、直接在两个数据库运行脚本。

```sql
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

3、两个工程的 pom.xml 添加 Seata 组件和 Nacos Config 组件。

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>

<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

4、给 JDBCTemplate 添加代理数据源

```java
package com.southwind;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(new DataSourceProxy(dataSource));
    }
}
```

```java
package com.southwind;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(new DataSourceProxy(dataSource));
    }

}
```

5、将 registry.conf 复制到两个工程的 resources 下。

6、给两个工程添加 bootstrap.yml 读取 Nacos 配置。

```yaml
spring:
  application:
    name: order
  cloud:
    nacos:
      config:
        server-addr: localhost:8848
        namespace: public
        group: SEATA_GROUP
    alibaba:
      seata:
        tx-service-group: ${spring.application.name}
```

```yaml
spring:
  application:
    name: pay
  cloud:
    nacos:
      config:
        server-addr: localhost:8848
        namespace: public
        group: SEATA_GROUP
    alibaba:
      seata:
        tx-service-group: ${spring.application.name}
```

tx-service-group 需要和 Nacos 配置中的名称一致。

![image-20200624172215657](C:/Users/ningn/AppData/Roaming/Typora/typora-user-images/image-20200624172215657.png)

7、在 Order 调用 Pay 处添加注解 @GlobalTransactional

```java
package com.southwind.controller;

import com.southwind.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/save")
    @GlobalTransactional
    public String save(){
        //订单
        this.orderService.save();
        int i = 10/0;
        //支付
        this.restTemplate.getForObject("http://localhost:8020/save",String.class);
        return "success";
    }
}
```























































```
// 特殊判断
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
```

 





# 三、 Spring Cloud Config 分布式配置



**Dalston.RELEASE**

**Spring Cloud Config为分布式系统中的外部配置提供服务器和客户端支持**。使用Config Server，您可以在所有环境中管理应用程序的外部属性。客户端和服务器上的概念映射与Spring `Environment`和`PropertySource`抽象相同，因此它们与Spring应用程序非常契合，但可以与任何以任何语言运行的应用程序一起使用。随着应用程序通过从开发人员到测试和生产的部署流程，您可以管理这些环境之间的配置，并确定应用程序具有迁移时需要运行的一切。服务器存储后端的默认实现使用git，因此它轻松支持标签版本的配置环境，以及可以访问用于管理内容的各种工具。很容易添加替代实现，并使用Spring配置将其插入。



**分布式系统面临的–配置文件问题**

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。spring cloud提供了configServer来解决这个问题，我们每一个微服务自己带着一个application.yml，那上百个的配置文件修改起来，令人头疼！

**什么是SpringCloud config分布式配置中心？**

![在这里插入图片描述](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120174222.png)



spring cloud config 分为**服务端**和**客户端**两部分。

服务端也称为 **分布式配置中心**，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密，解密信息等访问接口。

客户端则是**通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息**。配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理。并且可用通过git客户端工具来方便的管理和访问配置内容。



**spring cloud config 分布式配置中心能干嘛？**

- 集中式管理配置文件
- 不同环境，不同配置，动态化的配置更新，分环境部署，比如 /dev /test /prod /beta /release
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
- 当配置发生变动时，服务不需要重启，即可感知到配置的变化，并应用新的配置
- 将配置信息以REST接口的形式暴露



**spring cloud config 分布式配置中心与gitee整合**

由于spring cloud config 默认使用git来存储配置文件 (也有其他方式，比如自持SVN 和本地文件)，但是最推荐的还是git ，而且使用的是 http / https 访问的形式。

> 入门案例- 采用码云作为配置中心

1、新建gitee仓库`spring-cloud-config`

![image-20230120174506567](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120174506.png)

2、通过git提交一个`config-client.yml`的配置，配置文件内容如下

```yaml
spring:
  profiles:
    active: dev
--- 
server:
  port: 7474
# spring配置
spring:
  profiles: dev
  application:
    name: server-client-dev

--- 
server:
  port: 7475
# spring配置
spring:
  profiles: test
  application:
    name: server-client-test

```

3、新建一个config-server的项目，用作配置中心的跳转的服务器

3.1 在pom.xml中导入如下依赖

```xml
<!--相关依赖-->
    <dependencies>
        <!--springboot-web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--config-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
    </dependencies>
```

3.2 编写application.yml 文件，用于连接gitee上spring-cloud-config这个仓库

```yaml
spring:
  application:
    name: config-server
  # 连接远程仓库
  cloud:
    config:
      server:
        git:
          uri: https://gitee.com/yerenping/spring-cloud-config.git
server:
  port: 3344
```

4、测试“配置中心服务器config-server”到gitee上的连通性

访问gitee上的config-client.yml文件

![image-20230120175051865](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120175052.png)

5、创建客户端项目config-client

5.1 在pom.xml中添加如下依赖

```xml
<!--相关依赖-->
    <dependencies>
        <!--springboot-web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--config-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-client</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
```



5.2 编写系统级别的配置文件bootstrap.yml

```java
# 系统级别的配置
spring:
  cloud:
    config:
      # 读取远程配置文件，此处填写配置文件名
      name: config-client
      # 配置文件的环境
      profile: dev
      # 此处填写配置服务器的地址
      uri: http://localhost:3344
      # 远程配置分支名称
      lable: master
# name + profile +uri +lable ==  http://localhost:3344/master/config-client-dev.yml
```

5.3 编写用户级别的配置文件application.yml

```yml
# 用户级别的配置
spring:
  application:
    name: config-client-3355
```

5.4 编写测试控制器`TestController.java`，代码如下

```java
package com.yrp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YeRenping
 * @Date: 2023/1/20
 * @Description:
 */
@RestController
@Slf4j
public class TestController {
    @Value("${spring.application.name}")
    private  String applicationName;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public void t(){
        System.out.println(applicationName);
        System.out.println(port);
        log.debug("applicationName={}", applicationName);
        log.debug("port={}", port);
    }

}
```



6、测试客户端访问远程仓库配置文件 config-client.yml

6.1  profile: dev时候，启动项目，观察启动的端口号

- 设置为dev

![image-20230120175629522](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120175629.png)

- 观察项目启动时候的端口号

![image-20230120175715644](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120175715.png)

观察结果发现，为7474，说明该port是从gitee远程仓库中获取的



6.2  profile: test时候，启动项目，观察启动的端口号

观察结果发现，为7475，说明该port是从gitee远程仓库中获取的，与上面测试结果一致



6.3 访问`http://localhost:7474/config`，观察控制台打印输出



![image-20230120175916705](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230120175917.png)



















