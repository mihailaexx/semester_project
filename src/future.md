id generation -> student, employee, teacher

student -> registration for course, fix transcript/mark, implement rating
teacher -> complaint, mark
employee -> for now ok

Implement OrManager, FinanceManager, Research, News


Added for now OrManagerView, OrManagerController. I will add FinanceManager and Research after we finish the logic.

Strong suggestion to not change fields in model package to avoid confusion and possible bugs.
As you decided to implement the logic of our system, I suggest you to keep our design pattern (MVC).
If you have other vision on the functions of our models, then change the methods in model.people
and finalize the core logic in services.
Also, not all classes implemented fully, finalize it in services package

I will leave the implementation of core logic of FinanceManager and Researchers to you,
since мне впадлу и пойду готовиться к файналу по адс) 

Также нам надо будет переделать/доделать наши юмлки, думаю будет лучше если мы сделаем его абстрактно в каком нибудь genmymodel

Good luck & have fun!

