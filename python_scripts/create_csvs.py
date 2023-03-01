import pandas as pd
from datetime import date, timedelta, datetime
import random

def increment_date(cur_date: date) -> date:
    '''increments the date we are generating orders for by 1 day'''
    return cur_date + timedelta(days=1)

def add_random_item_sold(item_sold, order_num, prices):
    '''Create random item sold and add it to item sold table. Function called in add order'''
    index = random.randint(1, 25)
    cost = round(float(prices[index][1:]), 2)
    item = {"ITEM_ID": len(item_sold) + 1, "MENU_ITEM_ID": index + 1, "ORDER_ID": order_num, "ITEM_SOLD_QUANTITY": 1}
    item_sold = item_sold.append(item, ignore_index=True)
    return cost, item_sold

def add_order(orders, item_sold, date_str, prices):
    '''Create random order with 1-10 menu items in orders table and add each item used to item sold table.'''
    num_items = random.randint(1, 10)
    cost = 0
    order_num = len(orders) + 1
    for i in range(num_items):
        item_cost, item_sold = add_random_item_sold(item_sold, order_num, prices)
        cost += item_cost

    cost = round(cost, 2)
    order = {'ORDER_ID': order_num, 'DATE_ORDERED': date_str, 'ORDER_COST': cost}
    orders = orders.append(order, ignore_index=True)
    return cost, orders, item_sold

menu = pd.read_csv('database - Menu.csv')
prices = menu["NEMU_ITEM_COST (float)"].values.tolist()

date_format = "%Y-%m-%d"
start_date_string = "2022-02-27"
start_date = datetime.strptime(start_date_string, date_format).date()
curr_date = start_date

orders = pd.DataFrame(columns=["ORDER_ID", "DATE_ORDERED", "ORDER_COST"])
item_sold = pd.DataFrame(columns=['ITEM_ID', 'MENU_ITEM_ID', 'ORDER_ID', 'INVENTORY_ID', 'ITEM_SOLD_QUANTITY'])

total_cost = 0
for i in range(365):
    curr_date = increment_date(curr_date)

    if i % 21 == 0:
        num_orders = 600
    else:
        num_orders = 200

    for j in range(num_orders):
        cost, orders, item_sold = add_order(orders, item_sold, str(curr_date), prices)
        total_cost += cost
    print(str(i) + " : " + str(round(total_cost,2)))
orders.to_csv('Orders.csv', index=False)
item_sold.to_csv('ItemSold.csv', index=False)

print(round(total_cost, 2))