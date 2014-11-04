//
//  FEServiceListVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceListVC.h"
#import "FEServiceTableViewCell.h"
#import "FEServiceRequestVC.h"
#import "FEWebServiceManager.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "FEOrderListResponse.h"
#import "FEOrder.h"
#import "FEResult.h"

@interface FEServiceListVC ()<UITableViewDataSource,UITableViewDelegate>

@property (nonatomic, strong) UITableView *serviceTable;
@property (nonatomic, strong) NSMutableArray *serviceDatas;

@end

@implementation FEServiceListVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"ORDER_SERVICE");
        _serviceDatas = [NSMutableArray new];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    [self requestOrder];
}

-(void)initUI{
    
    [self loadRightCustomButtonItemWithTitle:FEString(@"ORDER_ADD") image:nil];
    
    UITableView *table = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
    table.backgroundColor = [UIColor clearColor];
    table.delegate = self;
    table.dataSource = self;
    [self.view addSubview:table];
    self.serviceTable = table;
    
    UIView *view = [UIView new];
    view.backgroundColor = [UIColor clearColor];
    [table setTableFooterView:view];

}

//请求历史
-(void)requestOrder{
    
    [self displayHUD:FEString(@"LOADING...")];
    FEPage *page = [[FEPage alloc] initWithPageSize:10 currentPage:1 count:1];
    FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"" value:@""];
    
    FEServiceOrederRequest *rdata = [[FEServiceOrederRequest alloc] initWithPage:page attribute:@[attr] userID:FELoginUser.userid];
    
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] orederList:rdata response:^(NSError *error, FEOrderListResponse *response) {
        
        [weakself hideHUD:YES];
        if (error) {
            ;
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:[NSString stringWithFormat:@"%@",error.userInfo] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
            [alert show];
        }else{
            if (response.result.errorCode.intValue != 0) {
                UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:[NSString stringWithFormat:@"error code %@",response.result.errorCode] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
                [alert show];
                return;
            }
            [weakself.serviceDatas removeAllObjects];
            [weakself.serviceDatas addObjectsFromArray:response.orderList];
            [weakself.serviceTable reloadData];
        }
    }];
}

-(void)rightbarpressed:(UIButton *)button{
//    NSLog(@"add service!");
    FEServiceRequestVC *serviceRequest = [FEServiceRequestVC new];
    [self.navigationController pushViewController:serviceRequest animated:YES];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    FEServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[FEServiceTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
    }
    FEOrder *order = self.serviceDatas[indexPath.row];
    [cell configWithOrder:order];
//    cell.numberLabel.text = order.orderID;
//    cell.typeLabel.text = order.orderType.boolValue?FEString(@"SEVICE_INSTALL"):FEString(@"SEVICE_REPAIR");
//    cell.statusLabel.text = order.orderStatus.integerValue?FEString(@"SERVICE_WAIT"):FEString(@"SERVICE_DEAL");
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.serviceDatas.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 50;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *header = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 50)];
    header.backgroundColor = FEColor(229, 229, 229, 1);
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(10, 20, 80, 30)];
    label.textAlignment = NSTextAlignmentLeft;
    label.text = FEString(@"ORDER_NUMBER");
    [header addSubview:label];
    
    label = [[FELabel alloc] initWithFrame:CGRectMake(100, 20, 80, 30)];
    label.textAlignment = NSTextAlignmentCenter;
    label.text = FEString(@"ORDER_TYPE");
    [header addSubview:label];
    
    label = [[FELabel alloc] initWithFrame:CGRectMake(240, 20, 75, 30)];
    label.textAlignment = NSTextAlignmentRight;
    label.text = FEString(@"ORDER_STATUS");
    [header addSubview:label];
    return header;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
