//
//  FECheckListVC.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECheckListVC.h"
//#import "FECheckListRequest.h"
//#import "FECheckListResponse.h"
#import "FECheckListRequest.h"
#import "FECheckListResponse.h"

#import "FECheckLogByIdRequest.h"
#import "FECheckLogByIdResponse.h"
#include "FEWebServiceManager.h"
#import "FECompany.h"
#import "FEMemoryCache.h"
#import "FECheckOperationVC.h"

@interface FECheckListVC (){
    NSMutableArray *_checkList;
}
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation FECheckListVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"日常巡检";
    _checkList = [NSMutableArray new];
    [self requestCheckList];
}

-(void)requestCheckList{
    
    FECheckListRequest *rdata = [[FECheckListRequest alloc] initWithCid:@(0)];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FECheckListResponse class] response:^(NSError *error, id response) {
        FECheckListResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            [_checkList addObjectsFromArray:rsp.checkItemList];
            [weakself.tableView reloadData];

        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FECheckItem *check = _checkList[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"checkItemCell" forIndexPath:indexPath];
    UILabel *title = (UILabel *)[cell viewWithTag:1];
    UILabel *detail = (UILabel *)[cell viewWithTag:2];
    UILabel *operation = (UILabel *)[cell viewWithTag:3];
    
    title.text = check.itemName;
    detail.text = check.itemSys;
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _checkList.count;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
//    UITableViewCell *cell = sender;
    NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
    FECheckItem *check = _checkList[indexPath.row];
    FECheckOperationVC *vc = segue.destinationViewController;
    vc.company = self.company;
    vc.checkItem = check;
}


@end
