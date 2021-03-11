import SwiftUI
import shared
import URLImage


struct ContentView: View {
    
    @ObservedObject var viewModel: ContentViewModel
    
    var body: some View {
        VStack(alignment: .leading) {
            userName
            customDivider
            userCountry
            userFollows
            customDivider
            userRepo
            userGists
            Spacer()
        }
    }
    
    private var userName: some View {
        HStack(spacing: 12) {
            if (viewModel.userData.avatar.isEmpty) {
                Circle()
                    .fill(Color.blue)
                    .frame(width: 64, height: 64)
            } else {
                URLImage(
                    url: URL(string: viewModel.userData.avatar)!,
                    failure: { _,_ in
                        Circle()
                            .fill(Color.blue)
                            .frame(width: 64, height: 64)
                    },
                    content: { image in
                        image.resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 64, height: 64)
                            .clipShape(Circle())
                    }
                ).onTapGesture { viewModel.openUserProfile() }
            }
            
            VStack(alignment: .leading, spacing: 8) {
                Text(viewModel.userData.name)
                    .font(.system(size: 18))
                    .fontWeight(.bold)
                Text(viewModel.userData.login)
                    .font(.system(size: 16))
                    .foregroundColor(.gray)
            }
            Spacer()
        }.padding(.top, 24).padding(.horizontal, 18)
    }
    
    private var userCountry: some View {
        VStack {
            Text(viewModel.userData.location)
                .padding(.top, 12)
                .padding(.horizontal, 18)
        }
    }
    
    private var userFollows: some View {
        HStack(spacing: 16) {
            HStack(spacing: 4) {
                Text(String(viewModel.userData.followers)).fontWeight(.bold)
                Text("followers")
            }
            
            HStack(spacing: 4) {
                Text(String(viewModel.userData.following)).fontWeight(.bold)
                Text("following")
            }
        }.padding(.top, 8).padding(.horizontal, 18)
    }
    
    private var userRepo: some View {
        VStack {
            HStack(spacing: 8) {
                Text("Repositories:")
                Text(String(viewModel.userData.publicRepos))
                Spacer()
                Image("RightArrow").frame(width: 12, height: 12)
            }.padding(.horizontal, 12)
            .padding(.vertical, 18)
            .background(Color("divider"))
            .cornerRadius(10)
        }.padding(.top, 12).padding(.horizontal, 18)
    }
    
    private var userGists: some View {
        VStack {
            HStack(spacing: 8) {
                Text("Gists:")
                Text(String(viewModel.userData.publicGists))
                Spacer()
                Image("RightArrow").frame(width: 12, height: 12)
            }.padding(.horizontal, 12)
            .padding(.vertical, 18)
            .background(Color("divider"))
            .cornerRadius(10)
        }.padding(.top, 4).padding(.horizontal, 18)
    }
    
    private var customDivider: some View {
        Divider().background(Color("divider")).padding(.horizontal, 18)
    }
}

