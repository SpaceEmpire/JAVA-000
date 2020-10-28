
#### java8 默认并行性GC，默认堆内存 1/4

```
java -XX:+PrintGCDetails GCLogAnalysis

java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

```

```
2020-10-27T21:01:42.009-0800: 0.234: [GC (Allocation Failure) [PSYoungGen: 33209K->5094K(38400K)] 33209K->10573K(125952K), 0.0056284 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 

// young 区
[PSYoungGen: 33209K->5094K(38400K)] 

// 整个区大小
[33209K->10573K(125952K), 0.0056284 secs]

// 用户时间，CPU时间，暂停时间
[Times: user=0.01 sys=0.00, real=0.01 secs] 
```

```
2020-10-27T21:01:42.098-0800: 0.323: [Full GC (Ergonomics) [PSYoungGen: 5118K->0K(138240K)] [ParOldGen: 61331K->62683K(129536K)] 66449K->62683K(267776K), [Metaspace: 2699K->2699K(1056768K)], 0.0134097 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 

[PSYoungGen: 5118K->0K(138240K)]
[ParOldGen: 61331K->62683K(129536K)]

```

#### 调整 GC 算法

##### 1、串行GC

```
// 发生 java.lang.OutOfMemoryError，发生 22次 Full GC 
// [Tenured: 87390K->87401K(87424K), 0.0195374 secs] 只有老年代在执行GC
// [Tenured: 87414K->87414K(87424K), 0.0017193 secs] 执行了Full GC 但是并没有完成内存回收 
java -XX:+UseSerialGC -Xmx128m -Xms128m -XX:+PrintGCDetails GCLogAnalysis


// 单线程GC，GC执行时间比较长
java -XX:+UseSerialGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis


// 只发生1次GC，执行时间比较长
java -XX:+UseSerialGC -Xmx2048m -Xms2048m -XX:+PrintGCDetails GCLogAnalysis


```
```
➜  demo java -XX:+UseSerialGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [DefNew: 139714K->17472K(157248K), 0.0782880 secs] 139714K->46167K(506816K), 0.0783320 secs] [Times: user=0.01 sys=0.02, real=0.07 secs] 
[GC (Allocation Failure) [DefNew: 157248K->17471K(157248K), 0.0592363 secs] 185943K->90383K(506816K), 0.0592701 secs] [Times: user=0.02 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 157168K->17471K(157248K), 0.0445542 secs] 230080K->128336K(506816K), 0.0445861 secs] [Times: user=0.01 sys=0.01, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 157247K->17470K(157248K), 0.0621373 secs] 268112K->180827K(506816K), 0.0622273 secs] [Times: user=0.02 sys=0.02, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 157246K->17471K(157248K), 0.0412787 secs] 320603K->223645K(506816K), 0.0413208 secs] [Times: user=0.01 sys=0.01, real=0.04 secs] 
[GC (Allocation Failure) [DefNew: 157247K->17469K(157248K), 0.0446017 secs] 363421K->270645K(506816K), 0.0446315 secs] [Times: user=0.02 sys=0.02, real=0.04 secs] 
[GC (Allocation Failure) [DefNew: 157245K->17471K(157248K), 0.0552776 secs] 410421K->314806K(506816K), 0.0553163 secs] [Times: user=0.02 sys=0.01, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0001281 secs][Tenured: 297335K->271136K(349568K), 0.0822758 secs] 454582K->271136K(506816K), [Metaspace: 2699K->2699K(1056768K)], 0.0827018 secs] [Times: user=0.05 sys=0.01, real=0.08 secs] 
[GC (Allocation Failure) [DefNew: 139776K->17466K(157248K), 0.0093852 secs] 410912K->314043K(506816K), 0.0094974 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [DefNew: 157242K->17471K(157248K), 0.0519565 secs] 453819K->363897K(506816K), 0.0519956 secs] [Times: user=0.02 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0001418 secs][Tenured: 346425K->311203K(349568K), 0.0519631 secs] 503673K->311203K(506816K), [Metaspace: 2699K->2699K(1056768K)], 0.0521523 secs] [Times: user=0.04 sys=0.00, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 139734K->139734K(157248K), 0.0000157 secs][Tenured: 311203K->320861K(349568K), 0.0528656 secs] 450937K->320861K(506816K), [Metaspace: 2699K->2699K(1056768K)], 0.0529268 secs] [Times: user=0.04 sys=0.00, real=0.06 secs] 
执行结束!共生成对象次数:6338
Heap
 def new generation   total 157248K, used 5678K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a058bbd8, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
  to   space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
 tenured generation   total 349568K, used 320861K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  91% used [0x00000007aaaa0000, 0x00000007be3f77c0, 0x00000007be3f7800, 0x00000007c0000000)
 Metaspace       used 2706K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```

```
➜  demo java -XX:+UseSerialGC -Xmx2048m -Xms2048m -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [DefNew: 559232K->69888K(629120K), 0.1493438 secs] 559232K->158014K(2027264K), 0.1494621 secs] [Times: user=0.06 sys=0.06, real=0.14 secs] 
执行结束!共生成对象次数:3693
Heap
 def new generation   total 629120K, used 495421K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,  76% used [0x0000000740000000, 0x0000000759f8f578, 0x0000000762220000)
  from space 69888K, 100% used [0x0000000766660000, 0x000000076aaa0000, 0x000000076aaa0000)
  to   space 69888K,   0% used [0x0000000762220000, 0x0000000762220000, 0x0000000766660000)
 tenured generation   total 1398144K, used 88126K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 1398144K,   6% used [0x000000076aaa0000, 0x00000007700afba8, 0x00000007700afc00, 0x00000007c0000000)
 Metaspace       used 2706K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```

##### 2、并行 GC

```
// java.lang.OutOfMemoryError
// 发生 17次 Full GC 
// [PSYoungGen: 14839K->14560K(29184K)] [ParOldGen: 87402K->87402K(87552K)] 都没有完成有效回收
java -XX:+UseParallelGC -Xmx128m -Xms128m -XX:+PrintGCDetails GCLogAnalysis

// 发生2次Full GC，每次GC执行时间相对较短，大概在30ms
java -XX:+UseParallelGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis

// 没有发送Full GC，GC 次数减少,但每次GC时间变长，大概在120ms
java -XX:+UseParallelGC -Xmx2048m -Xms2048m -XX:+PrintGCDetails GCLogAnalysis

```

```
➜  demo java -XX:+UseParallelGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 131584K->21498K(153088K)] 131584K->42212K(502784K), 0.0222747 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 153082K->21495K(153088K)] 173796K->85865K(502784K), 0.0479796 secs] [Times: user=0.03 sys=0.04, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 153079K->21499K(153088K)] 217449K->123606K(502784K), 0.0284725 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 152951K->21487K(153088K)] 255057K->162693K(502784K), 0.0311590 secs] [Times: user=0.03 sys=0.02, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 153071K->21498K(153088K)] 294277K->206270K(502784K), 0.0462899 secs] [Times: user=0.03 sys=0.03, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 153082K->21500K(80384K)] 337854K->249850K(430080K), 0.0322618 secs] [Times: user=0.02 sys=0.03, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 80237K->36152K(116736K)] 308587K->268324K(466432K), 0.0232366 secs] [Times: user=0.04 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 94493K->48287K(116736K)] 326664K->286023K(466432K), 0.0091258 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 107167K->57854K(116736K)] 344903K->306048K(466432K), 0.0213748 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 116579K->39374K(116736K)] 364773K->323582K(466432K), 0.0431998 secs] [Times: user=0.03 sys=0.03, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 98254K->22803K(116736K)] 382462K->343885K(466432K), 0.0340921 secs] [Times: user=0.03 sys=0.02, real=0.03 secs] 
[Full GC (Ergonomics) [PSYoungGen: 22803K->0K(116736K)] [ParOldGen: 321081K->240139K(349696K)] 343885K->240139K(466432K), [Metaspace: 2699K->2699K(1056768K)], 0.0631998 secs] [Times: user=0.09 sys=0.01, real=0.07 secs] 
[GC (Allocation Failure) [PSYoungGen: 58880K->16030K(116736K)] 299019K->256169K(466432K), 0.0035898 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 74756K->19871K(116736K)] 314896K->274181K(466432K), 0.0070458 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 78667K->20596K(116736K)] 332978K->294024K(466432K), 0.0103204 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 79361K->18406K(116736K)] 352789K->310502K(466432K), 0.0044549 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 77286K->18815K(116736K)] 369382K->328464K(466432K), 0.0056567 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
[Full GC (Ergonomics) [PSYoungGen: 18815K->0K(116736K)] [ParOldGen: 309648K->268092K(349696K)] 328464K->268092K(466432K), [Metaspace: 2699K->2699K(1056768K)], 0.0419318 secs] [Times: user=0.07 sys=0.01, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 58880K->17634K(116736K)] 326972K->285727K(466432K), 0.0026317 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 76514K->21508K(116736K)] 344607K->307131K(466432K), 0.0138392 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
执行结束!共生成对象次数:5746
Heap
 PSYoungGen      total 116736K, used 26691K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 8% used [0x00000007b5580000,0x00000007b5a8fcb8,0x00000007b8f00000)
  from space 57856K, 37% used [0x00000007bc780000,0x00000007bdc81180,0x00000007c0000000)
  to   space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
 ParOldGen       total 349696K, used 285623K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 81% used [0x00000007a0000000,0x00000007b16edc80,0x00000007b5580000)
 Metaspace       used 2706K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```

```
➜  demo java -XX:+UseParallelGC -Xmx2048m -Xms2048m -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 524800K->87026K(611840K)] 524800K->143942K(2010112K), 0.1145049 secs] [Times: user=0.06 sys=0.07, real=0.11 secs] 
[GC (Allocation Failure) [PSYoungGen: 611826K->87022K(611840K)] 668742K->255236K(2010112K), 0.1238249 secs] [Times: user=0.08 sys=0.11, real=0.12 secs] 
[GC (Allocation Failure) [PSYoungGen: 611822K->87039K(611840K)] 780036K->365474K(2010112K), 0.1466503 secs] [Times: user=0.09 sys=0.09, real=0.14 secs] 
执行结束!共生成对象次数:5953
Heap
 PSYoungGen      total 611840K, used 108402K [0x0000000795580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 524800K, 4% used [0x0000000795580000,0x0000000796a5ce70,0x00000007b5600000)
  from space 87040K, 99% used [0x00000007b5600000,0x00000007baaffce8,0x00000007bab00000)
  to   space 87040K, 0% used [0x00000007bab00000,0x00000007bab00000,0x00000007c0000000)
 ParOldGen       total 1398272K, used 278435K [0x0000000740000000, 0x0000000795580000, 0x0000000795580000)
  object space 1398272K, 19% used [0x0000000740000000,0x0000000750fe8d08,0x0000000795580000)
 Metaspace       used 2706K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 297K, capacity 386K, committed 512K, reserved 1048576K
```

```
➜  demo java -XX:+UseParallelGC -Xmx128m -Xms128m -XX:+PrintGCDetails GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 33280K->5108K(38400K)] 33280K->12011K(125952K), 0.0102669 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 38388K->5112K(38400K)] 45291K->26082K(125952K), 0.0191220 secs] [Times: user=0.03 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 38102K->5118K(38400K)] 59072K->37085K(125952K), 0.0102817 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 38106K->5116K(38400K)] 70074K->50463K(125952K), 0.0114015 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 38117K->5118K(38400K)] 83464K->62090K(125952K), 0.0064711 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
...
```

##### 3、CMS GC

```
// 出现 java.lang.OutOfMemoryError  GC无法有效回收
java -XX:+UseConcMarkSweepGC -Xmx128m -Xms128m -XX:+PrintGCDetails GCLogAnalysis

// 发生1次CMS GC，ParNew GC时间 33ms-64ms
java -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis

// 没有发生CMS GC，ParNew GC时间 32ms-82ms
java -XX:+UseConcMarkSweepGC -Xmx2048m -Xms2048m -XX:+PrintGCDetails GCLogAnalysis

// 没有发生CMS GC，每次GC时间变短
java -XX:+UseConcMarkSweepGC -Xmx4g -Xms4g -XX:+PrintGCDetails GCLogAnalysis
```

```
并发执行阶段，线程数默认CPU核心数 1/4 ，防止和业务抢占资源，影响业务执行。

// 阶段1 ：初始标记 （业务暂停）
[GC (CMS Initial Mark) [1 CMS-initial-mark: 204389K(349568K)] 225346K(506816K), 0.0026451 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

// 阶段2：并发标记
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.006/0.006 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]

// 阶段3：并发预清理
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[CMS-concurrent-abortable-preclean: 0.004/0.196 secs] [Times: user=0.25 sys=0.05, real=0.20 secs] 

// 阶段4：最终标记 （业务暂停）
[GC (CMS Final Remark) [YG occupancy: 18202 K (157248 K)][Rescan (parallel) , 0.0012524 secs][weak refs processing, 0.0000087 secs][class unloading, 0.0003189 secs][scrub symbol table, 0.0004208 secs][scrub string table, 0.0001172 secs][1 CMS-remark: 335404K(349568K)] 353606K(506816K), 0.0021878 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

// 阶段5：并发清除
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 

// 阶段6：并发重置
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```

##### 4、G1 GC

```
// java.lang.OutOfMemoryError
java -XX:+UseG1GC -Xmx128m -Xms128m -XX:+PrintGC GCLogAnalysis

// 发生多次G1 GC，young 时间10ms 以内
java -XX:+UseG1GC -Xmx512m -Xms512m -XX:+PrintGC GCLogAnalysis

// 没有发生G1 GC，young 时间 11ms-42ms
java -XX:+UseG1GC -Xmx2048m -Xms2048m -XX:+PrintGC GCLogAnalysis

// 没有发生G1 GC，young 时间 31ms-92ms
java -XX:+UseG1GC -Xmx4g -Xms4g -XX:+PrintGC GCLogAnalysis
```

```
// 阶段1：初始标记
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 276M->212M(512M), 0.0079991 secs]

// 阶段2：Root 扫描
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001287 secs]

// 阶段3：并发标记
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0028260 secs]

// 阶段4：再次标记
[GC remark, 0.0022017 secs]

// 阶段5：清理
[GC cleanup 219M->219M(512M), 0.0004906 secs]
```

```
[GC pause (G1 Evacuation Pause) (young) 288M->184M(512M), 0.0133891 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 276M->212M(512M), 0.0079991 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001287 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0028260 secs]
[GC remark, 0.0022017 secs]
[GC cleanup 219M->219M(512M), 0.0004906 secs]
[GC pause (G1 Evacuation Pause) (young) 386M->257M(512M), 0.0253791 secs]
[GC pause (G1 Evacuation Pause) (mixed) 263M->246M(512M), 0.0044730 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 247M->246M(512M), 0.0006455 secs]
```













