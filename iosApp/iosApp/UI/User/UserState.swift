//
//  UserState.swift
//  iosApp
//
//  Created by Dmytro on 13.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

final class UserState: ObservableObject {
    
    init() {
        UserStore().runtime(render: render)
    }
    
    @Published var user: UserStore.Props? = nil
    @Published var userData = GithubUserResponse(
        login: "",
        url: "",
        avatar: "",
        name: "",
        email: nil,
        followers: 0,
        following: 0,
        location: "",
        publicRepos: 0,
        publicGists: 0
    )
    @Published var isLoading: Bool = false
    
    func render(
        props: UserStore.Props,
        dispatch: @escaping (UserStore.Msg) -> KotlinUnit
    ) {
        handleProps(props: props)
        
        user = props
    }
    
    private func handleProps(props: UserStore.Props) {
        if(props.data is UserFetchResult.Content) {
            let state = props.data as! UserFetchResult.Content
            let response = state.data
            
            isLoading = false
            userData = response
        }
        if (props.data is UserFetchResult.Loading) {
            isLoading = true
        }
    }
    
    func update(msg: UserStore.Msg) {
        render(props: user!, dispatch: { (dispatch) in msg
            return.init()
        })
    }
    
}
