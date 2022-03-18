import Foundation

@objc public class CallDetector: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
