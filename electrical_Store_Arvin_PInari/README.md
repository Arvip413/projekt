# Electrical Store Management System
### Arvin Pinari

## Overview
This is a JavaFX application for managing an Electrical Store. It features a 3-level user system (Admin, Manager, Cashier), Invento
## Features Implemented
1. **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Abstraction, Generics.
2. **File Handling**: 
   - Binary IO (`.dat` files) for Users, Items, and Sales.
   - Text IO (`transaction_log.txt`) for loggin.
3. **MVC Architecture**: Code separated into Model, View, and Controller packags.
4. **Validation**: Inputvalidation using Regex.
5. **JavaFX UI**: Custom Login, Dashbard, Inventory, and Sales screens.




## Default Credentials
- **Administrator**: username: `admin`, password: `admin123`
- **Manager** :`manager`,`manager123`
- **Cashier** :`cashier`,`cashier123`


## Structure
- `src/com/arvin/store/model`: Data classes
- `src/com/arvin/store/view`: UI classes
- `src/com/arvin/store/controller`: Logic classes
- `src/com/arvin/store/data`: File Repositories
