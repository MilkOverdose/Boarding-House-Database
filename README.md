This systems aims to help boarding house owners manage their establishments through the use of managing tenants and their payments, room availability, caretakers and other staff. The SOLID principle implmenentaions are SRP, with each class
are designed to do one thing, and DIP where the controllers depends on the IDdatabaseConnnection interface instead of the MYSQLconnection class.
Creational design structure used here is the Builder, the tenants class has a builder class being used.
Structural design used is Facade having a dedicated navigation facade class that switches the scenes in the owner dashboard
Behavioral is Observer a code in TenantController automatically reacts when a selected row in the tenant table changes
