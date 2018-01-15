# Intelligence
Project of Intelligence NCNU 1061

# TSP是什麼？

- 要瞭解（TSP）是什麼，以及為什麼問題如此嚴重，讓我們簡要回顧一下這個問題的典型例子。

- 想象一下，你是一個推銷員，你已經得到了一個對面的地圖。在它上面，你看到地圖總共有20個地點，你被告知這是你的工作訪問這些地點的每個地方進行銷售。

- 在出發之前，您可能需要規劃一條路線，以便盡可能減少您的旅行時間。 幸運的，人類相當擅長這一點，我們可以輕鬆地制定一個相當好的路線，而不需要做更多的工作。 然而，當我們發現一條我們認為是最優的路線時，我們該如何測試它是否真的是最佳路線？

- 要理解為什麼要證明最佳路線如此困難，我們只需考慮一個類似的地圖。以三個地點為例，要找到一條路線，我們首先必須從地圖上的三個可能位置中選擇一個起始位置。 接下來，我們可以選擇第二個地點的2個城市，最後只剩下1個城市來完成我們的路線。 這意味著總共有3 x 2 x 1個不同的路線可供選擇。

- 這意味著，對於這個例子，只有6個不同的路線可供選擇。 所以對於只有3個位置的情況，計算6條路線中的每條路線並找到最短路線是相當簡單的。 如果你擅長數學，你可能已經意識到了這個問題。 可能路線的數量是要訪問的地點的數量的一個因子，而因子的問題是它們的增長速度非常快！

- 3的階乘是6
    - 例如，10的階乘是3628800，但20的階乘是巨大的，2432902008176640000。

- 因此，回到我們原來的問題，如果我們想要找到我們的20個位置的地圖的最短路線，我們將不得不評估2432902008176640000不同的路線！ 即使擁有現代計算能力，這是非常不切實際的，對於更大的問題，這幾乎是不可能的。


## 找到一個解決方案
- 雖然找到像我們這樣的問題的最佳解決方案可能不切實際，但是我們確實有算法可以讓我們發現接近最優解的算法，如最近鄰算法和群優化。 這些算法能夠驚人地快速找到旅行商問題的「足夠好的」解決方案。 但在本教程中，我們將使用GA算法作為我們的優化技術。

- TSP問題的解決方案要求我們以專門的方式建立一個GA算法。 
    - 例如，一個有效的解決方案將需要表示一個路線，其中每個位置至少包含一次而且只包含一次。 
    - 如果路線不止一次包含單個位置，或者完全錯過了一個位置，那麼這將是無效的，我們將會計算它的距離。

- 為了確保遺傳算法確實符合這個要求，需要特殊類型的mutation和swap方法。

- 首先，mutation方法只能對路線進行洗牌，
    - 不能在路線上增加或移除一個位置，
    - 否則會造成無效的解決方案。
    - 我們可以使用的一種類型的mutation方法是swap mutation。


- 通過swap mutation，可以隨機選擇路線中的兩個位置，然後簡單地交換它們的位置。 
    - 例如，如果我們將交換變換應用到下面的列表中，
    - [1,2,3,4,5]我們可能會以[1,2,5,4,3]結束。
    - 在這裡，位置3和位置5被切換為創建具有完全相同的值的新列表，只是不同的順序。 
    - 因為swap mutation只是交換預先存在的值，所以它不會創建與原始值相比缺少或重復值的列表，而這正是我們對TSP問題所要求的。
    - ![](http://www.theprojectspot.com/images/post-assets/swap_mutation.jpg)
￼


- 一種能夠產生有效路線的交叉方法是order crossover。 在這種方法中，我們從第一個父親中選擇一個子集，然後將該子集添加到後代。 然後，任何缺失的值都會添加到第二個父母的後代，以便找到它們。

- Parents
- ![](http://www.theprojectspot.com/images/post-assets/crossover_parents.jpg)
- Offspring
- ![](http://www.theprojectspot.com/images/post-assets/crossover_children.jpg)
￼

- 這裡的一部分路線是從第一個父母（6,7,8），並添加到後代的路線。 
- 接下來，缺少的路線位置從第二個父母開始依次添加。
- 第二個父母的路線中的第一個位置是9，這不在後代的路線中，所以它被添加到第一個可用的位置。
- 父母路線中的下一個位置是8，這是在後代的路線，所以它被跳過。 
- 這個過程繼續下去，直到後代沒有剩餘的空值。
- 如果正確實施，最終結果應該是一個路線，其中包含父母所做的所有職位，沒有職位缺失或重復。


## 在TSP問題的文獻中，由於地點通常被認為是城市，而路線被認為是旅遊，我們將在我們的代碼中採用標準的命名慣例。

### City.java：encode the cities.
- City()：// Constructs a randomly placed city
- City(int x,int y)： // Constructs a city at chosen x, y location
- getX()：// Gets city's x coordinate
- getY()：// Gets city's y coordinate
- distanceTo(City city)：// Gets the distance to given city

### TourManager.java ：holds all of our destination cities for our tour
- ArrayList destinationCities：// Holds our cities
- addCity(City city)：// Adds a destination city
- City getCity(int index)：// Get a city
- numberOfCities()： // Get the number of destination cities

### Tour.java：encode our routes, these are generally referred to as tours so we'll stick to the convention.
- ArrayList tour：// Holds our tour of cities
- fitness / distance = 0：// cache
- Tour()：// Constructs a blank tour
- generateIndividual()：// Creates a random individual
    - Collections.shuffle(tour)：// Randomly reorder the tour
- City getCity(int tourPosition)：// Gets a city from the tour
- setCity(int tourPosition, City city)：// Sets a city in a certain position within a tour
- getFitness()：// Gets the tours fitness
- getDistance()：// Gets the total distance of the tour
- tourSize()：// Get number of cities on our tour
- containsCity(City city)：// Check if the tour contains a city

### Population.java：hold a population of candidate tours
- Tour[] tours ：// Holds population of tours
- Population(int populationSize, boolean initialise) ：// Construct a population
- saveTour(int index, Tour tour)： // Saves a tour
- getTour(int index)：// Gets a tour from population
- Tour getFittest()：// Gets the best tour in the population
- populationSize()：// Gets population size


### GA.java：handle the working of the genetic algorithm and evolve our population of solutions.
- /* GA parameters */
    - mutationRate = 0.015
    - tournamentSize = 5
    - elitism = true

- Population evolvePopulation(Population pop)：// Evolves a population over one generation
- crossover(Tour parent1, Tour parent2)：// Applies crossover to a set of parents and creates offspring
- mutate(Tour tour)：// Mutate a tour using swap mutation
- tournamentSelection(Population pop)：// Selects candidate tour for crossover

### TSP_GA3.java：main method, add our cities and evolve a route for our travelling salesman problem.

## 最新版本
```
Java部分(TSP_GA3.java)
```
### 執行
* 輸入身分 (Old老人/Pregnant孕婦/Children小孩/Normal一般人)
* 先後印出
    * 總共有幾項設施
    * 演算法前的總路徑長
    * 演算法前的路徑
    * Evolution Finished(分隔)
    * 演算法後的總路徑長
    * 演算法後的路徑
    * 路徑+地圖(GUI)

### 新增功能
* TourManager
    * remove(因為身分限制，移除部分設施)
    * getSize(取得目前的設施數量)

* TSP_GA:
    * 讀檔(方便新增資料)
    * GUI(畫出路徑&地圖呈現)

# 參考來源:
[the Project Spot](http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5)

[NCNU IM Intelligence Lab](https://intelligence.im.ncnu.edu.tw)