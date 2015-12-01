# Rava
Execute some R stuff inside Java

## Instructions
### R-Studio
1. Open RStudio
2. Install package **Rserve** by typing in ```install.packages(Rserve)``` 
3. When successfully installed, type in ```library(Rserve)``` to start using the library
4. Type in ```Rserve()``` to start the Rserver Server on standard port 6311

### In Java
1. Rava needs the libraries *REngine.jar* and *RserveEngine.jar*
2. Instanciate Rava as you need it, for example

```java
package superpackage;

import java.util.ArrayList;

public class MySuperAwesomeStuffThatNeedsRInJava {
		public static void superFunction() {
			Rava superRava = new Rava();
			
			ArrayList<Double> myNumbers = new ArrayList<Double>();
			myNumbers.add(1337.0);
			myNumbers.add(42.0);
			myNumbers.add(1.0);
			myNumbers.add(2.0);
			
			ArrayList<Double> moreNumbers = new ArrayList<Double>();
			moreNumbers.add(4200.0);
			moreNumbers.add(9.0);
			moreNumbers.add(1.0);
			moreNumbers.add(2.3);
			
			System.out.println("Calculating accuracy measures of myNumbers and moreNumbers");
			superRava.calculateAccuracy(myNumbers, moreNumbers);
		}
}

```

That's it, have fun...
