import pandas as pd
from datetime import date
from datetime import datetime
from datetime import timedelta
import math

def incrementDate(curDate: date) -> date:
    '''increments the date we are generating orders for by 1 day'''
    curDate = curDate + timedelta(days=1)
    return curDate

def add_random_item_sold():
  menu_Contents = []
  with open("Menu.csv") as menu:
    #Content_list is the list that contains the read lines.     
    for line in menu:
      menu_Contents.append(line)
    
    for line in menu_Contents:
      menu_Contents[line].split(",")

    

  return cost

def add_order(orders, item_sold, date):
  num_items = math.random.randint(1,10)
  cost = 0

  for i in range(num_items):
    cost += add_random_item_sold(item_sold)

  orders.append({'ORDER_ID' : len(orders) - 1, 'DATE_ORDERED' : date, 'ORDER_COST' : cost })
  return cost

gameDay = False;
orders = pd.DataFrame(columns = ["ORDER_ID", "DATE_ORDERED", "ORDER_COST"])
item_sold = pd.dataFrame(columns = ["ITEM_ID", "MENU_ITEM_ID", "ORDER_ID", "INVENTORY_ID", "ITEM_SOLD_QUANTITY"])

dateFormat = "%Y-%m-%d"
startDateString = "2022-01-01"
startDate = datetime.strptime(startDateString, dateFormat).date()
currDate = startDate

total_cost = 0
for i in range(365):
  incrementDate(currDate)

  if(i % 21):
    num_orders = 4000
  else:
    num_orders = 15000

  for j in range(num_orders):
    total_cost += add_order(orders, item_sold, date)
print(total_cost)
orders.to_csv('Orders.csv')
item_sold.to_csv('ItemSold.csv')